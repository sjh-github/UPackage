package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PersonAndSmallTask;

import java.util.ArrayList;

public interface PersonAndSmallTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonAndSmallTask record);

    int insertSelective(PersonAndSmallTask record);

    PersonAndSmallTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonAndSmallTask record);

    int updateByPrimaryKey(PersonAndSmallTask record);

    ArrayList<Integer> selectSmallTaskIDByEmail(String email);

    PersonAndSmallTask selectBySmallTaskID(Integer smallTaskID);

}