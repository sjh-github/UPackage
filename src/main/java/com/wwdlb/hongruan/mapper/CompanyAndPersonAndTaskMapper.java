package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.CompanyAndPersonAndTask;

public interface CompanyAndPersonAndTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyAndPersonAndTask record);

    int insertSelective(CompanyAndPersonAndTask record);

    CompanyAndPersonAndTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyAndPersonAndTask record);

    int updateByPrimaryKey(CompanyAndPersonAndTask record);
}