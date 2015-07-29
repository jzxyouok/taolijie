package com.fh.taolijie.controller.restful;

import com.fh.taolijie.component.ResponseText;
import com.fh.taolijie.domain.ResumeModel;
import com.fh.taolijie.service.ResumeService;
import com.fh.taolijie.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by whf on 7/29/15.
 */
@RestController
@RequestMapping("/api/resume")
public class RestResumeController {
    @Autowired
    ResumeService resumeService;

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
        List<ResumeModel> reList = resumeService.getResumeListByIntend(cateId, pageNumber, pageSize);

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
        pageNumber = pageNumber * pageSize;
        List<ResumeModel> reList = resumeService.getResumeByGender(gender, pageNumber, pageSize);

        return new ResponseText(reList);
    }
}