package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SmallTask;

import java.util.ArrayList;

public interface SmallTaskMapper {
    int deleteByPrimaryKey(Integer smalltaskid);

    int insert(SmallTask record);

    int insertSelective(SmallTask record);

    SmallTask selectByPrimaryKey(Integer smalltaskid);

    int updateByPrimaryKeySelective(SmallTask record);

    int updateByPrimaryKey(SmallTask record);

    int getNumOfSmallTaskByState(String haveFinished);

    ArrayList<SmallTask> searchBySmallTaskName(String smallTaskName);

}