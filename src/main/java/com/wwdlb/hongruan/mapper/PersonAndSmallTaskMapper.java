package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PersonAndSmallTask;

public interface PersonAndSmallTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonAndSmallTask record);

    int insertSelective(PersonAndSmallTask record);

    PersonAndSmallTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonAndSmallTask record);

    int updateByPrimaryKey(PersonAndSmallTask record);
}