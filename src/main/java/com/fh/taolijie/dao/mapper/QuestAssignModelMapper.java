package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.QuestAssignModel;
import com.fh.taolijie.domain.QuestAssignModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestAssignModelMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest_assign
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest_assign
     *
     * @mbggenerated
     */
    int insert(QuestAssignModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest_assign
     *
     * @mbggenerated
     */
    int insertSelective(QuestAssignModel record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest_assign
     *
     * @mbggenerated
     */
    QuestAssignModel selectByPrimaryKey(Integer id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest_assign
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QuestAssignModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest_assign
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(QuestAssignModel record);

    boolean checkMemberIdAndQuestIdExists(@Param("memId") Integer memId, @Param("questId") Integer questId);
}