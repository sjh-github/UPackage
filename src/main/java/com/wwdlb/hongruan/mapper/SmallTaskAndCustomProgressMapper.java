package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SmallTaskAndCustomProgress;

import java.util.ArrayList;

public interface SmallTaskAndCustomProgressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmallTaskAndCustomProgress record);

    int insertSelective(SmallTaskAndCustomProgress record);

    SmallTaskAndCustomProgress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmallTaskAndCustomProgress record);

    int updateByPrimaryKey(SmallTaskAndCustomProgress record);

    ArrayList<SmallTaskAndCustomProgress> getSmallTaskAndCustomProgressBySmallTaskID(Integer smallTaskID);

    SmallTaskAndCustomProgress selectByCustomID(Integer customProgressID);
}