package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.Protocol;

public interface ProtocolMapper {
    int deleteByPrimaryKey(Integer protocolid);

    int insert(Protocol record);

    int insertSelective(Protocol record);

    Protocol selectByPrimaryKey(Integer protocolid);

    int updateByPrimaryKeySelective(Protocol record);

    int updateByPrimaryKey(Protocol record);
}