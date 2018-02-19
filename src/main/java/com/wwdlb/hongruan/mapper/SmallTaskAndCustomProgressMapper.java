package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SmallTaskAndCustomProgress;

public interface SmallTaskAndCustomProgressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmallTaskAndCustomProgress record);

    int insertSelective(SmallTaskAndCustomProgress record);

    SmallTaskAndCustomProgress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmallTaskAndCustomProgress record);

    int updateByPrimaryKey(SmallTaskAndCustomProgress record);
}