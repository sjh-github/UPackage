package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PersonAndContract;

public interface PersonAndContractMapper {
    int deleteByPrimaryKey(String email);

    int insert(PersonAndContract record);

    int insertSelective(PersonAndContract record);

    PersonAndContract selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(PersonAndContract record);

    int updateByPrimaryKey(PersonAndContract record);
}