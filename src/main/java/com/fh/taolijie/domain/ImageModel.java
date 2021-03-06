package com.fh.taolijie.domain;

import com.fh.taolijie.domain.job.JobPostModel;
import com.fh.taolijie.domain.sh.SHPostModel;

public class ImageModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_resource.id
     *
     * @mbggenerated
     */
    private Integer id;

    private Integer jobPostId;
    private JobPostModel jobPost;

    private Integer shPostId;
    private SHPostModel shPost;

    private Integer newsId;
    private NewsModel news;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_resource.file_name
     *
     * @mbggenerated
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_resource.extension
     *
     * @mbggenerated
     */
    private String extension;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_resource.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_resource.member_id
     *
     * @mbggenerated
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_resource.bin_data
     *
     * @mbggenerated
     */
    private byte[] binData;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_resource.id
     *
     * @return the value of image_resource.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_resource.id
     *
     * @param id the value for image_resource.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public JobPostModel getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPostModel jobPost) {
        this.jobPost = jobPost;
    }

    public Integer getShPostId() {
        return shPostId;
    }

    public void setShPostId(Integer shPostId) {
        this.shPostId = shPostId;
    }

    public SHPostModel getShPost() {
        return shPost;
    }

    public void setShPost(SHPostModel shPost) {
        this.shPost = shPost;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public NewsModel getNews() {
        return news;
    }

    public void setNews(NewsModel news) {
        this.news = news;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_resource.file_name
     *
     * @return the value of image_resource.file_name
     *
     * @mbggenerated
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_resource.file_name
     *
     * @param fileName the value for image_resource.file_name
     *
     * @mbggenerated
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_resource.extension
     *
     * @return the value of image_resource.extension
     *
     * @mbggenerated
     */
    public String getExtension() {
        return extension;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_resource.extension
     *
     * @param extension the value for image_resource.extension
     *
     * @mbggenerated
     */
    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_resource.type
     *
     * @return the value of image_resource.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_resource.type
     *
     * @param type the value for image_resource.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_resource.member_id
     *
     * @return the value of image_resource.member_id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_resource.member_id
     *
     * @param memberId the value for image_resource.member_id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_resource.bin_data
     *
     * @return the value of image_resource.bin_data
     *
     * @mbggenerated
     */
    public byte[] getBinData() {
        return binData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_resource.bin_data
     *
     * @param binData the value for image_resource.bin_data
     *
     * @mbggenerated
     */
    public void setBinData(byte[] binData) {
        this.binData = binData;
    }
}