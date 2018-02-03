package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PersonAndProtocol;

public interface PersonAndProtocolMapper {
    int deleteByPrimaryKey(String email);

    int insert(PersonAndProtocol record);

    int insertSelective(PersonAndProtocol record);

    PersonAndProtocol selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(PersonAndProtocol record);

    int updateByPrimaryKey(PersonAndProtocol record);
}