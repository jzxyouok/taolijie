package com.fh.taolijie.service.impl;

import com.fh.taolijie.dao.mapper.ApplicationIntendModelMapper;
import com.fh.taolijie.domain.resume.ApplicationIntendModel;
import com.fh.taolijie.service.ApplicationIntendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanghongfei on 15-6-19.
 */
@Service
public class DefaultAppIntendService implements ApplicationIntendService {
    @Autowired
    ApplicationIntendModelMapper aiMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationIntendModel> getByIntend(Integer categoryId, int pageNumber, int pageSize) {
        return aiMapper.getByIntend(categoryId, pageNumber, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationIntendModel> getByResume(Integer resumeId) {
        return aiMapper.getByResume(resumeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationIntendModel> getByResumeInBatch(List<Integer> resumeIdList) {
        return aiMapper.getByResumeInBatch(resumeIdList);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void addIntend(ApplicationIntendModel model) {
        aiMapper.insert(model);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void deleteIntend(ApplicationIntendModel model) {
        aiMapper.delete(model);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void deleteByResume(Integer resumeId) {
        aiMapper.deleteByResumeId(resumeId);
    }
}
