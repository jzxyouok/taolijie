package com.fh.taolijie.domain;

import java.util.Date;

public class QuestCoModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collection_quest.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collection_quest.created_time
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collection_quest.member_id
     *
     * @mbggenerated
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collection_quest.quest_id
     *
     * @mbggenerated
     */
    private Integer questId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column collection_quest.quest_title
     *
     * @mbggenerated
     */
    private String questTitle;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column collection_quest.id
     *
     * @return the value of collection_quest.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collection_quest.id
     *
     * @param id the value for collection_quest.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column collection_quest.created_time
     *
     * @return the value of collection_quest.created_time
     *
     * @mbggenerated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collection_quest.created_time
     *
     * @param createdTime the value for collection_quest.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column collection_quest.member_id
     *
     * @return the value of collection_quest.member_id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collection_quest.member_id
     *
     * @param memberId the value for collection_quest.member_id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column collection_quest.quest_id
     *
     * @return the value of collection_quest.quest_id
     *
     * @mbggenerated
     */
    public Integer getQuestId() {
        return questId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collection_quest.quest_id
     *
     * @param questId the value for collection_quest.quest_id
     *
     * @mbggenerated
     */
    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column collection_quest.quest_title
     *
     * @return the value of collection_quest.quest_title
     *
     * @mbggenerated
     */
    public String getQuestTitle() {
        return questTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column collection_quest.quest_title
     *
     * @param questTitle the value for collection_quest.quest_title
     *
     * @mbggenerated
     */
    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle == null ? null : questTitle.trim();
    }
}