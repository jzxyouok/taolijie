package com.fh.taolijie.domain;

import com.fh.taolijie.domain.middle.ResumeWithIntend;
import com.fh.taolijie.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 与简历相关的非数据库操作逻辑, 不需要事务支持
 * Created by whf on 8/28/15.
 */
@Service
public class ResumeQueryService {
    /**
     * 给ResumeModel对象的intend赋值.
     * 即将意向设置到对应的简历中
     *
     * @param withList 带意向信息和简历id的中间对象
     * @param reList 简历List
     * @return 赋好值的简历List
     */
    public List<ResumeModel> assignIntend(List<ResumeWithIntend> withList, List<ResumeModel> reList) {
        withList.forEach( with -> {

            Optional<ResumeModel> resume = reList.stream()
                    .filter(r -> r.getId().equals(with.getResumeId()))
                    .findAny();

            if (resume.isPresent()) {
                resume.get().getIntend().add(with.getCateName());
            }
        });

        return reList;
    }
}