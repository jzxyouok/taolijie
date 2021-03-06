package com.fh.taolijie.service.quest.impl;

import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.constant.acc.AccFlow;
import com.fh.taolijie.dao.mapper.MemberModelMapper;
import com.fh.taolijie.dao.mapper.QuestModelMapper;
import com.fh.taolijie.dao.mapper.SysConfigModelMapper;
import com.fh.taolijie.dao.mapper.TljAuditModelMapper;
import com.fh.taolijie.domain.SysConfigModel;
import com.fh.taolijie.domain.TljAuditModel;
import com.fh.taolijie.domain.acc.CashAccModel;
import com.fh.taolijie.domain.acc.MemberModel;
import com.fh.taolijie.domain.quest.QuestModel;
import com.fh.taolijie.exception.checked.acc.BalanceNotEnoughException;
import com.fh.taolijie.exception.checked.acc.CashAccNotExistsException;
import com.fh.taolijie.exception.checked.quest.AuditNotEnoughException;
import com.fh.taolijie.exception.checked.quest.QuestNotFoundException;
import com.fh.taolijie.exception.checked.quest.RequestRepeatedException;
import com.fh.taolijie.service.acc.CashAccService;
import com.fh.taolijie.service.quest.TljAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by whf on 10/1/15.
 */
@Service
public class DefaultTljAuditService implements TljAuditService {
    @Autowired
    private TljAuditModelMapper auditMapper;

    @Autowired
    private SysConfigModelMapper confMapper;

    @Autowired
    private CashAccService accService;

    @Autowired
    private MemberModelMapper memMapper;

    @Autowired
    private QuestModelMapper questMapper;

    @Autowired
    private FeeCalculator feeService;

    @Override
    @Transactional(readOnly = true)
    public TljAuditModel findById(Integer auditId) {
        return auditMapper.selectByPrimaryKey(auditId);
    }

    @Override
    @Transactional(readOnly = true)
    public ListResult<TljAuditModel> findBy(TljAuditModel cmd) {
        List<TljAuditModel> list = auditMapper.findBy(cmd);
        long tot = auditMapper.countFindBy(cmd);

        return new ListResult<>(list, tot);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void addAudit(TljAuditModel example)
            throws BalanceNotEnoughException, CashAccNotExistsException, RequestRepeatedException, QuestNotFoundException {

        // 检查对同一个任务是否重复申请
        if (auditMapper.checkMemberAndQuestExists(example.getEmpId(), example.getQuestId())) {
            throw new RequestRepeatedException("");
        }

        // 填写冗余字段 username
        MemberModel mem = memMapper.selectByPrimaryKey(example.getEmpId());
        example.setEmpUsername(mem.getUsername());
        // 填写冗余字段 questTitle
        QuestModel quest = questMapper.selectByPrimaryKey(example.getQuestId());
        if (null == quest) {
            throw new QuestNotFoundException("");
        }

        example.setQuestTitle(quest.getTitle());
        example.setCreatedTime(new Date());


        // 计算金额
        BigDecimal amt = calculateFee(example.getTotAmt());

        // 扣钱
        CashAccModel acc = accService.findByMember(example.getEmpId());
        if (null == acc) {
            throw new CashAccNotExistsException("");
        }
        accService.reduceAvailableMoney(acc.getId(), amt, AccFlow.CONSUME);

        auditMapper.insertSelective(example);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculateFee(Integer amt) {
        return feeService.computeTljAuditFee(amt);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void updateAudit(TljAuditModel example) {
        auditMapper.updateByPrimaryKeySelective(example);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void decreaseLeftAmt(Integer auditId) throws AuditNotEnoughException {
        // 查询剩余数量
        // 该查询会加行锁
        long left = auditMapper.queryLeftAmt(auditId);
        if (left <= 0) {
            throw new AuditNotEnoughException();
        }

        // 数量-1
        auditMapper.decreaseLeftAmt(auditId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void decreaseLeftAmtByQuest(Integer questId) throws AuditNotEnoughException {
        // 查询剩余数量
        // 该查询会加行锁
        long left = auditMapper.queryLeftAmtByQuest(questId);
        if (left <= 0) {
            throw new AuditNotEnoughException();
        }

        // 数量-1
        auditMapper.decreaseLeftAmtByQuest(questId);

    }
}
