package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.StuCertiModel;
import com.fh.taolijie.domain.StuCertiModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StuCertiModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StuCertiModel record);

    int insertSelective(StuCertiModel record);

    StuCertiModel selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(StuCertiModel record);

    int updateByPrimaryKey(StuCertiModel record);

    List<StuCertiModel> findBy(StuCertiModel example);
    long countFindBy(StuCertiModel example);


    boolean checkApplyExists(Integer memId);
}