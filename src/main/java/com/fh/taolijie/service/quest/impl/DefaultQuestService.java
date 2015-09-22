package com.fh.taolijie.service.quest.impl;

import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.constant.quest.AssignStatus;
import com.fh.taolijie.dao.mapper.MemberModelMapper;
import com.fh.taolijie.dao.mapper.QuestAssignModelMapper;
import com.fh.taolijie.dao.mapper.QuestModelMapper;
import com.fh.taolijie.dao.mapper.SysConfigModelMapper;
import com.fh.taolijie.domain.MemberModel;
import com.fh.taolijie.domain.QuestAssignModel;
import com.fh.taolijie.domain.QuestModel;
import com.fh.taolijie.exception.checked.quest.QuestAssignedException;
import com.fh.taolijie.exception.checked.quest.QuestZeroException;
import com.fh.taolijie.service.quest.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 轻兼职业务实现
 * Created by whf on 9/21/15.
 */
@Service
public class DefaultQuestService implements QuestService {
    @Autowired
    private QuestModelMapper questMapper;

    @Autowired
    private SysConfigModelMapper configMapper;

    @Autowired
    private FeeCalculator feeCal;

    @Autowired
    private QuestAssignModelMapper assignMapper;

    @Autowired
    private MemberModelMapper memMapper;


    @Override
    @Transactional(readOnly = false)
    public void publishQuest(QuestModel model) {
        // 获取费率
        int rate = configMapper.selectByPrimaryKey(1).getQuestFeeRate();
        // 单个任务的原始价格
        BigDecimal single = model.getAward();
        // 单个任务的最终价格
        single = feeCal.calculateFee(rate, single);
        // 将最终价格设置到model中
        model.setFinalAward(single);

        model.setCreatedTime(new Date());

        questMapper.insertSelective(model);
    }

    /**
     * 领取任务。
     * 需要行锁。
     *
     * @param memId 领取任务的用户id.
     * @param questId 任务id
     */
    @Override
    @Transactional(readOnly = false)
    public void assignQuest(Integer memId, Integer questId)
            throws QuestAssignedException, QuestZeroException {

        // 检查任务是否已经领取
        boolean repeat = assignMapper.checkMemberIdAndQuestIdExists(memId, questId);
        if (repeat) {
            throw new QuestAssignedException("");
        }

        // 判断剩余任务数量
        // 这句查询会加行锁
        int leftAmt = questMapper.selectQuestLeftAmountWithLock(questId);
        if (leftAmt <= 0) {
            // 任务已经没了
            throw new QuestZeroException("");
        }


        // 领取任务
        // 向分配表中插入记录
        QuestAssignModel assignModel = new QuestAssignModel();
        assignModel.setMemberId(memId);
        assignModel.setQuestId(questId);
        assignModel.setAssignTime(new Date());
        // 设置冗余字段username
        MemberModel m = memMapper.selectByPrimaryKey(memId);
        assignModel.setUsername(m.getUsername());
        // 设置冗余字段quest_title
        QuestModel quest = questMapper.selectByPrimaryKey(questId);
        assignModel.setQuestTitle(quest.getTitle());
        assignModel.setStatus(AssignStatus.ASSIGNED.code());
        assignMapper.insertSelective(assignModel);


        // 任务剩余数量减少1
        questMapper.decreaseLeftAmount(questId);


        // 方法结束 == 事务结束，行锁释放
    }

    @Override
    public ListResult<QuestModel> findByCate(Integer cateId, int pn, int ps) {
        return null;
    }

    @Override
    public ListResult<QuestModel> findByCate(Integer cateId, BigDecimal min, BigDecimal max, int pn, int ps) {
        return null;
    }
}
