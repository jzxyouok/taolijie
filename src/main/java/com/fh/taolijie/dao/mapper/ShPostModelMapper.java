package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.cache.annotation.RedisCache;
import com.fh.taolijie.cache.annotation.RedisEvict;
import com.fh.taolijie.domain.SHPostModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShPostModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    @RedisEvict(SHPostModel.class)
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    @RedisEvict(SHPostModel.class)
    int insert(SHPostModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    @RedisEvict(SHPostModel.class)
    int insertSelective(SHPostModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    @RedisCache(SHPostModel.class)
    SHPostModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    @RedisEvict(SHPostModel.class)
    int updateByPrimaryKeySelective(SHPostModel record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    @RedisEvict(SHPostModel.class)
    int updateByPrimaryKey(SHPostModel record);


    @RedisCache(SHPostModel.class)
    List<SHPostModel> getAll(Map<String, Integer> pageMap);
    @RedisCache(SHPostModel.class)
    long countGetAll();


    @RedisCache(SHPostModel.class)
    List<SHPostModel> getInBatch(List<Integer> idList);


    @RedisCache(SHPostModel.class)
    List<SHPostModel> getByCategory(@Param("categoryId") Integer categoryId, @Param("orderByPageView") boolean orderByPageView, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
    @RedisCache(SHPostModel.class)
    long countGetByCategory(Integer categoryId);


    @RedisCache(SHPostModel.class)
    List<SHPostModel> getByMember(@Param("memberId") Integer memberId, @Param("filtered") boolean filtered, @Param("pageNumber") int pageNumber ,@Param("pageSize") int pageSize);
    @RedisCache(SHPostModel.class)
    long countGetByMember(@Param("memberId") Integer memberId, @Param("filtered") boolean filtered);


    @RedisCache(SHPostModel.class)
    List<SHPostModel> searchBy(SHPostModel model);
    @RedisCache(SHPostModel.class)
    long countSearchBy(SHPostModel model);


    @RedisCache(SHPostModel.class)
    List<SHPostModel> findBy(SHPostModel model);
    @RedisCache(SHPostModel.class)
    long countFindBy(SHPostModel model);


    @RedisCache(SHPostModel.class)
    List<SHPostModel> getSuedPost(@Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);
    @RedisCache(SHPostModel.class)
    long countGetSuedPost();


    @RedisEvict(SHPostModel.class)
    void increaseComplaint(Integer postId);

    @RedisEvict(SHPostModel.class)
    void increasePageView(Integer postId);

    @RedisEvict(SHPostModel.class)
    void increaseLike(Integer postId);
    @RedisEvict(SHPostModel.class)
    void decreaseLike(Integer postId);

    @RedisEvict(SHPostModel.class)
    int setDeleted(@Param("postId") Integer postId, @Param("delete") boolean delete);
}