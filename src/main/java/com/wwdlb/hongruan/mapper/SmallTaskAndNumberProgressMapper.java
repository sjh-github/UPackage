package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;

public interface SmallTaskAndNumberProgressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmallTaskAndNumberProgress record);

    int insertSelective(SmallTaskAndNumberProgress record);

    SmallTaskAndNumberProgress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmallTaskAndNumberProgress record);

    int updateByPrimaryKey(SmallTaskAndNumberProgress record);
}