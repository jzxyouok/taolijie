package com.fh.taolijie.service.acc;

import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.constant.quest.OrderStatus;
import com.fh.taolijie.domain.PayOrderModel;

/**
 * 充值业务接口
 * Created by whf on 9/21/15.
 */
public interface ChargeService {
    /**
     * 发起充值申请
     * @param model
     */
    void chargeApply(PayOrderModel model);

    /**
     * 更新审核状态
     * @param orderId
     * @param status
     * @param memo
     */
    boolean updateStatus(Integer orderId, OrderStatus status, String memo);

    /**
     * 查询指定现金账户的订单
     * @return
     */
    ListResult<PayOrderModel> findByAcc(Integer accId, int pn, int ps);

    /**
     * 查询指定现金账户指定状态的订单
     * @return
     */
    ListResult<PayOrderModel> findByAcc(Integer accId, OrderStatus status, int pn, int ps);
}
