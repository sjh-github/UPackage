package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.CustomProgress;

public interface CustomProgressMapper {
    int deleteByPrimaryKey(Integer customprogressid);

    int insert(CustomProgress record);

    int insertSelective(CustomProgress record);

    CustomProgress selectByPrimaryKey(Integer customprogressid);

    int updateByPrimaryKeySelective(CustomProgress record);

    int updateByPrimaryKey(CustomProgress record);
}