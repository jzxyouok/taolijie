package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.SHPostModel;
import org.apache.ibatis.annotations.Param;
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
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    int insert(SHPostModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    int insertSelective(SHPostModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    SHPostModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SHPostModel record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table second_hand_post
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SHPostModel record);

    List<SHPostModel> getAll(Map<String, Integer> pageMap);

    List<SHPostModel> getInBatch(List<Integer> idList);

    List<SHPostModel> getByCategory(@Param("categoryId") Integer categoryId, @Param("orderByPageView") boolean orderByPageView, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    List<SHPostModel> getByMember(@Param("memberId") Integer memberId, @Param("filtered") boolean filtered, @Param("pageNumber") int pageNumber ,@Param("pageSize") int pageSize);

    List<SHPostModel> searchBy(SHPostModel model);

    List<SHPostModel> findBy(SHPostModel model);

    List<SHPostModel> getSuedPost(@Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);

    void increaseComplaint(Integer postId);

    void increasePageView(Integer postId);

    void increaseLike(Integer postId);
}