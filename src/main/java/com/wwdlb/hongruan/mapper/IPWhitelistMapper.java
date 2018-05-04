package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.IPWhitelist;

import java.util.ArrayList;

public interface IPWhitelistMapper {
    int deleteByPrimaryKey(Integer ipwhitelistid);

    int insert(IPWhitelist record);

    int insertSelective(IPWhitelist record);

    IPWhitelist selectByPrimaryKey(Integer ipwhitelistid);

    int updateByPrimaryKeySelective(IPWhitelist record);

    int updateByPrimaryKey(IPWhitelist record);

    ArrayList<IPWhitelist> selectAll();
}