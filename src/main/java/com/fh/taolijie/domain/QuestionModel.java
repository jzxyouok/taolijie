package com.fh.taolijie.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class QuestionModel {
    private Integer id;

    private Date createdTime;

    @NotNull
    private String content;

    //@NotNull
    private Integer questId;

    @NotNull
    private Integer orderIndex;

    @NotNull
    private Integer type;

    private Integer userAmt;

    private Integer correctAmt;

    private Integer answerAmt;

    /**
     * 问题选项
     */
    @NotNull
    private List<QuestionOptModel> opts;

    public static boolean validate(QuestionModel model) {
        // 只要有一个字段为null就返回false
        return !(null == model.content || null == model.orderIndex || null == model.type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.created_time
     *
     * @param createdTime the value for quest_question.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.content
     *
     * @return the value of quest_question.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.content
     *
     * @param content the value for quest_question.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.quest_id
     *
     * @return the value of quest_question.quest_id
     *
     * @mbggenerated
     */
    public Integer getQuestId() {
        return questId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.quest_id
     *
     * @param questId the value for quest_question.quest_id
     *
     * @mbggenerated
     */
    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.order_index
     *
     * @return the value of quest_question.order_index
     *
     * @mbggenerated
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.order_index
     *
     * @param orderIndex the value for quest_question.order_index
     *
     * @mbggenerated
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.type
     *
     * @return the value of quest_question.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.type
     *
     * @param type the value for quest_question.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.user_amt
     *
     * @return the value of quest_question.user_amt
     *
     * @mbggenerated
     */
    public Integer getUserAmt() {
        return userAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.user_amt
     *
     * @param userAmt the value for quest_question.user_amt
     *
     * @mbggenerated
     */
    public void setUserAmt(Integer userAmt) {
        this.userAmt = userAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.correct_amt
     *
     * @return the value of quest_question.correct_amt
     *
     * @mbggenerated
     */
    public Integer getCorrectAmt() {
        return correctAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.correct_amt
     *
     * @param correctAmt the value for quest_question.correct_amt
     *
     * @mbggenerated
     */
    public void setCorrectAmt(Integer correctAmt) {
        this.correctAmt = correctAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quest_question.answer_amt
     *
     * @return the value of quest_question.answer_amt
     *
     * @mbggenerated
     */
    public Integer getAnswerAmt() {
        return answerAmt;
    }

    public List<QuestionOptModel> getOpts() {
        return opts;
    }

    public void setOpts(List<QuestionOptModel> opts) {
        this.opts = opts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quest_question.answer_amt
     *
     * @param answerAmt the value for quest_question.answer_amt
     *
     * @mbggenerated
     */
    public void setAnswerAmt(Integer answerAmt) {
        this.answerAmt = answerAmt;
    }
}