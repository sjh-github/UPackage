package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SmallTaskAndProvidePersonEmail;

import java.util.ArrayList;

public interface SmallTaskAndProvidePersonEmailMapper {
    int deleteByPrimaryKey(Integer smalltaskandprovideemailid);

    int insert(SmallTaskAndProvidePersonEmail record);

    int insertSelective(SmallTaskAndProvidePersonEmail record);

    SmallTaskAndProvidePersonEmail selectByPrimaryKey(Integer smalltaskandprovideemailid);

    int updateByPrimaryKeySelective(SmallTaskAndProvidePersonEmail record);

    int updateByPrimaryKey(SmallTaskAndProvidePersonEmail record);

    int deleteBySmallTaskID(Integer smallTaskID);

    ArrayList<SmallTaskAndProvidePersonEmail> selectByProvidePersonEmail(String email);
}