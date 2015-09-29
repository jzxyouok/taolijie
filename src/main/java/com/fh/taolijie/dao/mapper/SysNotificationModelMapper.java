package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.noti.SysNotificationModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysNotificationModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNotificationModel record);

    int insertSelective(SysNotificationModel record);

    SysNotificationModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNotificationModel record);

    int updateByPrimaryKey(SysNotificationModel record);

    List<SysNotificationModel> findSysByAccessRange(@Param("list") List<String> roleList, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
    long countFindSysByAccessRange(List<String> roleList);

    List<SysNotificationModel> findSysByRangeAndFilter(@Param("readList") List<Integer> readList, @Param("rangeList") List<String> rangeList, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
    long countFindSysByRangeAndFilter(@Param("readList") List<Integer> readList, @Param("rangeList") List<String> rangeList);


    List<SysNotificationModel> findAll(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
    int countFindAll();

}