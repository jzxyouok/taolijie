package com.fh.taolijie.controller.restful.job;

import cn.fh.security.credential.Credential;
import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.component.ResponseText;
import com.fh.taolijie.constant.ErrorCode;
import com.fh.taolijie.constant.PostType;
import com.fh.taolijie.domain.job.JobPostCategoryModel;
import com.fh.taolijie.domain.job.JobPostModel;
import com.fh.taolijie.service.PVService;
import com.fh.taolijie.service.job.JobPostCateService;
import com.fh.taolijie.service.job.JobPostService;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.PageUtils;
import com.fh.taolijie.utils.SessionUtils;
import com.fh.taolijie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wanghongfei on 15-6-19.
 */
@RestController
@RequestMapping(value = "/api/job")
public class RestJobController {
    @Autowired
    JobPostService jobService;

    @Autowired
    JobPostCateService cateService;

    @Autowired
    private PVService pvService;

    /**
     * 得到所有兼职信息, 最新的在前
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText getAllPost(@RequestParam(defaultValue = "0") int pageNumber,
                                    @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {
        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);
        ListResult<JobPostModel> jobList = jobService.getAllJobPostList(pageNumber, pageSize);

        jobService.checkExpired(jobList.getList());
        pvService.pvMatch(jobList.getList(), PostType.JOB);

        return new ResponseText(jobList);
    }

    /**
     * 过虑查询
     * @return
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText filter(JobPostModel model) {
        model.setPageNumber(PageUtils.getFirstResult(model.getPageNumber(), model.getPageSize()));
        ListResult<JobPostModel> list = jobService.findByExample(model);

        jobService.checkExpired(list.getList());
        pvService.pvMatch( list.getList(), PostType.JOB );

        return new ResponseText(list);
    }

    /**
     * 得到指定用户发布的兼职信息
     * @param memberId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/user/{memberId}", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText getPostByMember(@PathVariable("memberId") Integer memberId,
                                        HttpServletRequest req,
                                        @RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {

        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);
        ListResult<JobPostModel> list = jobService.getJobPostListByMember(memberId, pageNumber, pageSize);

        jobService.checkExpired(list.getList());
        pvService.pvMatch( list.getList(),PostType.JOB );

        return new ResponseText(list);
    }

    /**
     * 根据分类查询post
     * @param categoryId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText getPostByCategory(@PathVariable("categoryId") Integer categoryId,
                                    @RequestParam(defaultValue = "0") int pageNumber,
                                    @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {
        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);
        ListResult<JobPostModel> list = jobService.getJobPostListByCategory(categoryId, pageNumber, pageSize);

        jobService.checkExpired(list.getList());
        pvService.pvMatch( list.getList(), PostType.JOB );

        return new ResponseText(list);
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    public ResponseText getPostInBatch(@RequestParam(value = "ids") String idString) {
        List<Integer> idList = StringUtils.toIdList(idString);
        if (null == idList) {
            return new ResponseText(ErrorCode.INVALID_PARAMETER);
        }

        ListResult<JobPostModel> postList = jobService.getPostListByIds(idList.toArray(new Integer[0]));

        jobService.checkExpired(postList.getList());
        pvService.pvMatch(postList.getList(), PostType.JOB);

        return new ResponseText(postList);
    }

    /**
     * 搜索
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText searchPost(JobPostModel model,
                                   @RequestParam(defaultValue = "0") int pageNumber,
                                   @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {
        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);

        ListResult<JobPostModel> postList = jobService.runSearch(model, pageNumber, pageSize);

        jobService.checkExpired(postList.getList());
        pvService.pvMatch( postList.getList(), PostType.JOB );

        return new ResponseText(postList);
    }

    /**
     * 根据id得到post
     * @param postId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText getPostById(@PathVariable(value = "id") Integer postId,
                                    HttpServletRequest req) {

        JobPostModel model = jobService.findJobPostWithPV(postId);
        // 检查是不是已经删除
        if (true == model.isDeleted()) {
            // 如果是已经删除
            // 只有管理员用户可以访问
            Credential credential = SessionUtils.getCredential(req);
            // 未登陆或不是管理员
            if (null == credential || false == SessionUtils.isAdmin(credential)) {
                // 返回错误信息
                return new ResponseText(ErrorCode.NOT_FOUND);
            }
        }

        jobService.checkExpired(Arrays.asList(model));

        return new ResponseText(model);
    }

    /**
     * 根据id查找分类
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/cate/{id}", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText getCategoryById(@PathVariable(value = "id") Integer cateId) {
        //JobPostCategoryModel cate = cateService.findCategory(cateId);
        JobPostCategoryModel cate = cateService.findCategory(cateId);

        return new ResponseText(cate);
    }

    /**
     * 查询所有兼职分类
     * @return
     */
    @RequestMapping(value = "/cate/list", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText getCategoryList() {
        ListResult<JobPostCategoryModel> list = cateService.getCategoryList(0, Integer.MAX_VALUE);

        return new ResponseText(list);
    }
}
