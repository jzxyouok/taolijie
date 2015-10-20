package com.fh.taolijie.service;

import com.fh.taolijie.domain.BulletinModel;

import java.util.List;

/**
 * Created by wanghongfei on 15-3-31.
 */
public interface BulletinService {
    void addBulletin(BulletinModel model);

    void deleteBulletin(Integer id);

    /**
     * 查询所有公告
     */
    List<BulletinModel> getAll(int firstResult, int capacity);

    BulletinModel findOne(Integer id);
}
