package com.fh.taolijie.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wanghongfei on 15-3-4.
 */
@Entity
@Table(name = "review", schema = "", catalog = "taolijie")
public class ReviewEntity {
    private Integer id;
    private String content;
    private Date time;
    private JobPostEntity jobPost;
    private MemberEntity member;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewEntity that = (ReviewEntity) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    public JobPostEntity getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPostEntity jobPost) {
        this.jobPost = jobPost;
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }
}