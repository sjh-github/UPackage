package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PersonAndTask;

public interface PersonAndTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonAndTask record);

    int insertSelective(PersonAndTask record);

    PersonAndTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonAndTask record);

    int updateByPrimaryKey(PersonAndTask record);
}