package com.fh.taolijie.dao.mapper.v2;

import com.fh.taolijie.domain.v2.SchoolActCategoryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolActCategoryModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    int insert(SchoolActCategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    int insertSelective(SchoolActCategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    SchoolActCategoryModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolActCategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SchoolActCategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolActCategoryModel record);
}