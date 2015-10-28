package com.fh.taolijie.test.utils;

import com.fh.taolijie.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by wanghongfei on 15-3-31.
 */
public class StringUtilsTest {
    @Test
    public void testBuildJPQL() {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("name", "wanghongfei");
        parmMap.put("age", "20");
        parmMap.put("home", "none");

        Map.Entry<String, String> order = new AbstractMap.SimpleEntry<String, String>("time", "DESC");

        String query = StringUtils.buildQuery("job", "JobPostEntity", parmMap, order);

        Assert.assertEquals("SELECT job FROM JobPostEntity job WHERE job.name=:name AND job.age=:age AND job.home=:home ORDER BY job.time DESC", query);
    }

    @Test
    public void testLikeJPQL() {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("name", "wanghongfei");
        parmMap.put("age", "20");
        parmMap.put("home", "none");

        Map.Entry<String, String> order = new AbstractMap.SimpleEntry<String, String>("time", "DESC");

        String query = StringUtils.buildLikeQuery("JobPostEntity", parmMap, order);
        System.out.println(query);

        //Assert.assertEquals("SELECT job FROM JobPostEntity job WHERE job.name=:name AND job.age=:age AND job.home=:home ORDER BY job.time DESC", query);

    }

    @Test
    public void testRemoveFromString() {
        String str = "1;2;3;4;5;";

        String result = StringUtils.removeFromString(str, "3");
        Assert.assertEquals("1;2;4;5;", result);

        result = StringUtils.removeFromString(str, "1");
        Assert.assertEquals("2;3;4;5;", result);

        result = StringUtils.removeFromString(str, "5");
        Assert.assertEquals("1;2;3;4;", result);



        str = "12;123;931;0;";
        result = StringUtils.removeFromString(str, "12");
        Assert.assertEquals("123;931;0;", result);

        result = StringUtils.removeFromString(str, "123");
        Assert.assertEquals("12;931;0;", result);

        result = StringUtils.removeFromString(str, "0");
        Assert.assertEquals("12;123;931;", result);

        result = StringUtils.removeFromString(str, "111");
        Assert.assertEquals("12;123;931;0;", result);
    }

    @Test
    public void repeatTest() {
        Set<String> set = new HashSet<>();
        for (int i = 0 ; i < 100000 ; ++i) {
            String str = StringUtils.randomString(15);
            if (true == set.contains(str)) {
                Assert.assertTrue("重复的token", false);
            }
            set.add(str);
        }
    }

    @Test
    public void testCheckIdExists() {
        String ids = "1;2;3;4;5;";
        Assert.assertTrue(StringUtils.checkIdExists(ids, "1"));
        Assert.assertTrue(StringUtils.checkIdExists(ids, "2"));
        Assert.assertTrue(StringUtils.checkIdExists(ids, "3"));
        Assert.assertTrue(StringUtils.checkIdExists(ids, "4"));
        Assert.assertTrue(StringUtils.checkIdExists(ids, "5"));

        ids = "123;234;435;1;2;34;";
        Assert.assertTrue(StringUtils.checkIdExists(ids, "123"));
        Assert.assertTrue(StringUtils.checkIdExists(ids, "2"));
        Assert.assertTrue(StringUtils.checkIdExists(ids, "34"));
        Assert.assertFalse(StringUtils.checkIdExists(ids, "4"));
        Assert.assertFalse(StringUtils.checkIdExists(ids, "5"));
    }

    @Test
    public void testValidateLadderString() {
        String[] strs = {
                null, null, "OK", "OK"
        };

        Assert.assertTrue(StringUtils.validateLadderString(strs));

        strs = Arrays.asList( "OK", null, "OK", "OK" ).toArray(new String[]{});
        Assert.assertFalse(StringUtils.validateLadderString(strs));

        strs = Arrays.asList( null, null, null, null ).toArray(new String[]{});
        Assert.assertTrue(StringUtils.validateLadderString(strs));



        strs = null;
        Assert.assertTrue(StringUtils.validateLadderString(strs));

        strs = Arrays.asList( null, null, "OK", null ).toArray(new String[]{});
        Assert.assertFalse(StringUtils.validateLadderString(strs));
    }
}
