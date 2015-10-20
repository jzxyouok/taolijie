package com.fh.taolijie.controller.restful;

import cn.fh.security.credential.Credential;
import cn.fh.security.utils.CredentialUtils;
import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.component.ResponseText;
import com.fh.taolijie.constant.ErrorCode;
import com.fh.taolijie.domain.resume.ResumeModel;
import com.fh.taolijie.domain.middle.ResumeWithIntend;
import com.fh.taolijie.exception.checked.InvalidNumberStringException;
import com.fh.taolijie.service.ResumeService;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.PageUtils;
import com.fh.taolijie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by whf on 7/29/15.
 */
@RestController
@RequestMapping("/api/resume")
public class RestResumeController {
    @Autowired
    ResumeService resumeService;

    /**
     * 简历库
     * @return
     */
    @RequestMapping(value = "/list", produces = Constants.Produce.JSON)
    public ResponseText allResumes(@RequestParam(defaultValue = "0") int pageNumber,
                                   @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize,
                                    HttpSession session) {
        ListResult<ResumeModel> resumes;

        pageNumber = pageNumber * pageSize;

        // 查找所有简历
        Credential credential = CredentialUtils.getCredential(session);
        // 如果是商家
        if (null != credential && Constants.RoleType.EMPLOYER.toString().equals(credential.getRoleList().get(0))) {
            resumes = resumeService.findByAuthes(pageNumber, pageSize, Constants.AccessAuthority.ALL, Constants.AccessAuthority.EMPLOYER);
        } else {
            resumes = resumeService.findByAuthes(pageNumber, pageSize, Constants.AccessAuthority.ALL);
        }

        return new ResponseText(resumes);
    }

    /**
     * 根据id查询简历
     * @param resumeId
     * @return
     */
    @RequestMapping(value = "/{resumeId}", produces = Constants.Produce.JSON)
    public ResponseText findById(@PathVariable("resumeId") Integer resumeId) {
        List<ResumeModel> reList = resumeService.getResumesByIds(0, 0, null, resumeId);

        if (false == reList.isEmpty()) {
            return new ResponseText(reList.get(0));
        }

        return new ResponseText();
    }

    /**
     * 根据求职意向查询
     * @param cateId
     * @param pageNumber
     * @param pageSize
     * @return
     */ @RequestMapping(value = "/intend/{cateId}", produces = Constants.Produce.JSON)
    public ResponseText findByIntend(@PathVariable("cateId") Integer cateId,
                                     @RequestParam(defaultValue = "0") int pageNumber,
                                     @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {
        pageNumber = pageNumber * pageSize;
        ListResult<ResumeModel> reList = resumeService.getResumeListByIntend(cateId, pageNumber, pageSize);

        return new ResponseText(reList);
    }


    /**
     * 按性别筛选
     * @return
     */
    @RequestMapping(value = "/gender/{gender}", produces = Constants.Produce.JSON)
    public ResponseText findByGender(@PathVariable("gender") String gender,
                                @RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {
        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);
        ListResult<ResumeModel> reList = resumeService.getResumeByGender(gender, pageNumber, pageSize);

        return new ResponseText(reList);
    }


    /**
     * 根据意向和性别过虑
     * @return
     */
    @RequestMapping(value = "/genderAndIntend", produces = Constants.Produce.JSON)
    public ResponseText filterByGenderAndIntend(@RequestParam("gender") String gender,
                                                @RequestParam("intendId") Integer intendId,
                                                @RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {
        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);

        ListResult<ResumeModel> lr = resumeService.findByGenderAndIntend(intendId, gender, pageNumber, pageSize);

        return new ResponseText(lr);
    }

    /**
     * 过虑查询
     * @return
     */
    @RequestMapping(value = "/filter", produces = Constants.Produce.JSON)
    public ResponseText filter( ResumeModel model,
                                @RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") int pageSize) {

        pageNumber = PageUtils.getFirstResult(pageNumber, pageSize);
        model.setPageNumber(pageNumber);
        model.setPageSize(pageSize);

        // 是否根据意向查询
        String ids = model.getIntendIds();
        if (null != ids) {
            try {
                List<Integer> idList = StringUtils.splitIntendIds(ids);
                model.setIntendIdList(idList);

            } catch (InvalidNumberStringException e) {
                return new ResponseText(ErrorCode.INVALID_PARAMETER);
            }
        }

        // 简历
        ListResult<ResumeModel> lr = resumeService.findBy(model);

        // 把简历id取出转换成List
        List<Integer> idList = lr.getList().stream()
                .map(ResumeModel::getId)
                .collect(Collectors.toList());

        // 查询简历对应的意向
        if (!idList.isEmpty()) {
            List<ResumeWithIntend> withList = resumeService.findIntendForResume(idList);

            // 将意向设置到对应简历中
            //List<ResumeModel> list = reQueryService.assignIntend(withList, lr.getList());
            List<ResumeModel> list = resumeService.assignIntend(withList, lr.getList());

            lr.setList(list);
        }

        return new ResponseText(lr);

    }
}
