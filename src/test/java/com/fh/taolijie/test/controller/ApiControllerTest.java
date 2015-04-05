package com.fh.taolijie.test.controller;

import cn.fh.security.utils.CredentialUtils;
import com.fh.taolijie.controller.ApiController;
import com.fh.taolijie.domain.*;
import com.fh.taolijie.service.impl.*;
import com.fh.taolijie.test.service.repository.BaseSpringDataTestClass;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.Print;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wynfrith on 15-4-2.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {ApiController.class,
                                 DefaultJobPostService.class,
                                 DefaultSHPostService.class,
                                 DefaultResumeService.class,
                                 DefaultAccountService.class,
                                 DefaultReviewService.class,
                                 DefaultJobPostCategoryService.class,
                                 DefaultSearchService.class,
                                 DefaultSHPostCategoryService.class})
public class ApiControllerTest extends BaseSpringDataTestClass {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    ApiController apiController;
    private MockMvc mockMvc;



    RoleEntity role1;
    RoleEntity role2;
    RoleEntity role3;
    MemberEntity mem1;
    MemberEntity mem2;
    NewsEntity news1;
    ReviewEntity review1;
    JobPostEntity job1;
    ResumeEntity resume1;
    SecondHandPostEntity sh1;
    SecondHandPostCategoryEntity shCate1;
    JobPostCategoryEntity jobCate1;
    NotificationEntity notification1;


    @Before
    public void before(){
        Print.print("准备数据");
        // UserController userController = new UserController();
        if(mockMvc==null){
            this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
            initData();
        }

        Print.print("准备完成");
    }

    public void initData(){

        /**
         *  创建用户
         *
         */
        /* mem1 学生用户 wynfrith*/
        mem1 = new MemberEntity();
        mem1.setUsername("wynfrith");
        mem1.setEmail("wangfucheng56@gmail.com");
        mem1.setPassword(CredentialUtils.sha("wfc5582563"));
        mem1.setQq("1252264267");
        mem1.setValid(false);
        mem1.setAge(21);
        /*加入三个工作id*/
        mem1.setAppliedJobIds("1" + Constants.DELIMITER + "2" + Constants.DELIMITER + "3");
        //mem1.setBlockList();
        mem1.setGender("男");
        //mem1.setCompanyName("");
        mem1.setName("王富诚");
        //  mem1.setComplaint();
        // mem1.setVerified();
        // mem1.setSecondHandPostCollection();
        //  mem1.setJobPostCollection();
        // mem1.setReviewCollection();
        //mem1.setResumeCollection();
        // mem1.setProfilePhotoId();
        //mem1.setReplyList();
        // mem1.setPhone();
        //mem1.setNotificationCollection();
        //mem1.setNewsCollection();
        em.persist(mem1);



        /* mem2 商家用户 wfc5582563*/
        mem2 = new MemberEntity();
        mem2.setUsername("wfc5582563");
        mem2.setEmail("wfc5582563@126.com");
        mem2.setPassword(CredentialUtils.sha("qq123456"));
        mem2.setQq("123456789");
        mem2.setAge(28);
        /*加入三个工作id*/
        mem2.setGender("男");
        mem2.setName("王先生");
        em.persist(mem2);



        /**
         * 创建member与role映射
         */
        MemberRoleEntity memRole1 = new MemberRoleEntity();
        memRole1.setRole(role1);
        memRole1.setMember(mem1);

        List<MemberRoleEntity> memRoleList = new ArrayList<>();
        memRoleList.add(memRole1);
        mem1.setMemberRoleCollection(memRoleList);
        em.persist(memRole1);

        MemberRoleEntity memRole2 = new MemberRoleEntity();
        memRole2.setRole(role2);
        memRole2.setMember(mem2);
        mem2.setMemberRoleCollection(Arrays.asList(memRole2));
        em.persist(memRole2);

        /**
         * 兼职分类
         */
        jobCate1 = new JobPostCategoryEntity();
        jobCate1.setName("电脑维护人员");
        jobCate1.setLevel(1);
        jobCate1.setMemo("修电脑的");
        jobCate1.setThemeColor("#66ccff");
        em.persist(jobCate1);

        /**
         * 兼职
         */
        job1 = new JobPostEntity();
        job1.setMember(mem1);
        job1.setWorkTime("工作时间在周六周日");
        job1.setWorkPlace("工作地点在淄博");
        job1.setTitle("兼职标题");

        job1.setCategory(jobCate1);
        jobCate1.setJobPostCollection(new ArrayList<>());
        jobCate1.getJobPostCollection().add(job1);
        mem1.setJobPostCollection(new ArrayList<>());
        mem1.getJobPostCollection().add(job1);
        em.persist(job1);

        JobPostEntity job;
        for(int i=0;i<10;i++){
            job = new JobPostEntity();
            job.setMember(mem1);
            job.setWorkTime("工作时间在周六周日");
            job.setWorkPlace("工作地点在淄博");
            job.setTitle("兼职标题"+i);
            job.setCategory(jobCate1);
            jobCate1.setJobPostCollection(new ArrayList<>());
            jobCate1.getJobPostCollection().add(job);
            em.persist(job);
        }

        /**
         * 二手分类
         */
        shCate1 = new SecondHandPostCategoryEntity();
        shCate1.setName("废旧电子设备");
        shCate1.setLevel(1);
        shCate1.setMemo("电子设备");
        shCate1.setThemeColor("#66ccff");
        em.persist(shCate1);

        /**
         * 二手
         */
        sh1 = new SecondHandPostEntity();
        sh1.setMember(mem1);
        sh1.setTitle("二手标题");
        sh1.setDescription("二手物品的描述");

        sh1.setCategory(shCate1);
        shCate1.setPostCollection(new ArrayList<>());
        shCate1.getPostCollection().add(sh1);
        mem1.setSecondHandPostCollection(new ArrayList<>());
        mem1.getSecondHandPostCollection().add(sh1);
        em.persist(sh1);

        /**
         * 简历
         *
         */

        /*学生mem1 发的简历*/
        resume1= new ResumeEntity();
        resume1.setMember(mem1);
        resume1.setEmail("wangfucheng56@gmail.com");
        resume1.setGender("男");
        resume1.setQq("1252264267");
        resume1.setIntroduce("我叫王富诚");
        mem1.setResumeCollection(Arrays.asList(resume1));
        em.persist(resume1);
    }

    @Test
    public void testJobcate() throws  Exception{
        MvcResult result = mockMvc.perform(get("/api/cate/job")
                .contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testJobList() throws  Exception{
        MvcResult result = mockMvc.perform(get("/api/list/job/1")
                .contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testResumeList() throws  Exception{
        MvcResult result = mockMvc.perform(get("/api/list/resume/2")
                .contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testShCate() throws  Exception{
        MvcResult result = mockMvc.perform(get("/api/cate/sh")
                .contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testSHList() throws  Exception{
        MvcResult result = mockMvc.perform(get("/api/list/sh/1")
                .contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }




}
