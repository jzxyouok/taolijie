package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.QuestModel;
import com.fh.taolijie.domain.QuestModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int countByExample(QuestModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int deleteByExample(QuestModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int insert(QuestModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int insertSelective(QuestModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    List<QuestModel> selectByExample(QuestModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    QuestModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") QuestModel record, @Param("example") QuestModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") QuestModel record, @Param("example") QuestModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QuestModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quest
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(QuestModel record);
}