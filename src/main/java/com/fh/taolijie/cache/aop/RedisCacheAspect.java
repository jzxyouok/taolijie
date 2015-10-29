package com.fh.taolijie.cache.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.classmate.Annotations;
import com.fh.taolijie.cache.annotation.NoCache;
import com.fh.taolijie.cache.annotation.RedisCache;
import com.fh.taolijie.cache.annotation.RedisEvict;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.JedisUtils;
import com.fh.taolijie.utils.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截Mybatis的Mapper接口方法，执行缓存逻辑.
 * Created by whf on 7/8/15.
 */
@Aspect
@Component
public class RedisCacheAspect {
    public static final Logger infoLog = LogUtils.getInfoLogger();


    @Autowired
    private JedisPool jedisPool;

    /**
     * 方法调用前，先查询缓存。如果存在缓存，则返回缓存数据，阻止方法调用;
     * 如果没有缓存，则调用业务方法，然后将结果放到缓存中
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.fh.taolijie.dao.mapper.*Post*.select*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.get*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.find*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.search*(..))")
    public Object cache(ProceedingJoinPoint jp) throws Throwable {
        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();

        // 判断是否存在NoCache注解
        // 如果存在，跳过逻辑逻辑
        if (me.isAnnotationPresent(NoCache.class)) {
            if (infoLog.isDebugEnabled()) {
                infoLog.debug("方法{}标有@NoCache注解, 禁止缓存", jp.getSignature().getName());
            }
            return jp.proceed(jp.getArgs());
        }

        Jedis jedis = JedisUtils.getClient(jedisPool);

        // 得到类名、方法名和参数
        //String clazzName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        // 根据类名，方法名和参数生成key
        String key = genKey("", methodName, args);
        if (infoLog.isDebugEnabled()) {
            infoLog.debug("生成key:{}", key);
        }

        // 得到被代理的方法上的注解
        Class modelType = me.getAnnotation(RedisCache.class).value();

        // result是方法的最终返回结果
        Object result = null;

        // 检查redis中是否有缓存
        String value = null;
        try {
            value = jedis.hget(modelType.getName(), key);

            if (null == value) {
                // 缓存未命中
                if (infoLog.isDebugEnabled()) {
                    infoLog.debug("缓存未命中");
                }

                // 调用数据库查询方法
                result = jp.proceed(args);

                // 序列化查询结果
                String json = serialize(result);

                String hashName = modelType.getName();



                // 序列化结果放入缓存
                // 判断hash名是否存在
                Boolean exist = jedis.exists(hashName);
                Pipeline pip = jedis.pipelined();
                // 更新hash
                pip.hset(hashName, key, json);
                // 如果不存在，设置过期时间
                if (false == exist) {
                    pip.expire(hashName, (int) Constants.HASH_EXPIRE_TIME);
                }
                pip.sync();

            } else {
                // 缓存命中
                if (infoLog.isDebugEnabled()) {
                    infoLog.debug("缓存命中, value = {}", value);
                }

                // 得到被代理方法的返回值类型
                Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();

                // 反序列化从缓存中拿到的json
                result = deserialize(value, returnType, modelType);

                if (infoLog.isDebugEnabled()) {
                    infoLog.debug("反序列化结果 = {}", result);
                }
            }
        } catch (Exception ex) {
            // 如果扔异常，说明Redis出了问题
            // 此时直接查数据库，绕开Redis

            // 日志记录
            String errMsg = LogUtils.getStackTrace(ex);
            LogUtils.getErrorLogger().error(errMsg);

            // 执行数据库查询方法
            result = jp.proceed(args);
            return result;

        } finally {
            jedisPool.returnResourceObject(jedis);
        }


        return result;
    }

    /**
     * 在方法调用前清除缓存，然后调用业务方法
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.fh.taolijie.dao.mapper.*Post*.insert*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.update*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.delete*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.increase*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.decrease*(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.complaint(..))" +
            "|| execution(* com.fh.taolijie.dao.mapper.*Post*.set*(..))")
    public Object evictCache(ProceedingJoinPoint jp) throws Throwable {

        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();


        // 如果标记了@NoCache注解，则不执行缓存操作
        if (me.isAnnotationPresent(NoCache.class)) {
            return jp.proceed(jp.getArgs());
        }

        // 得到被代理的方法上的注解
        RedisEvict reAn = me.getAnnotation(RedisEvict.class);
        // 如果没有任何注解
        // 则不执行任何操作
        if (null == reAn) {
            return jp.proceed(jp.getArgs());
        }

        Class[] modelType = reAn.value();

        for (Class clazz : modelType) {
            if (infoLog.isDebugEnabled()) {
                infoLog.debug("清空缓存:{}", clazz.getName());
            }

            // 清除对应缓存
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.del(clazz.getName());

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                JedisUtils.returnJedis(jedisPool, jedis);
            }
        }


        return jp.proceed(jp.getArgs());
    }



    /**
     * 根据类名、方法名和参数生成key
     * @param clazzName
     * @param methodName
     * @param args 方法参数
     * @return
     */
    protected String genKey(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append(Constants.DELIMITER);
        sb.append(methodName);
        sb.append(Constants.DELIMITER);

        for (Object obj : args) {
            sb.append(obj.toString());
            sb.append(Constants.DELIMITER);
        }

        return sb.toString();
    }

    protected String serialize(Object target) {
        return JSON.toJSONString(target);
    }

    protected Object deserialize(String jsonString, Class clazz, Class modelType) {
        // 序列化结果应该是List对象
        if (clazz.isAssignableFrom(List.class)) {
            return JSON.parseArray(jsonString, modelType);
        }

        // 序列化结果是普通对象
        return JSON.parseObject(jsonString, clazz);
    }
}
