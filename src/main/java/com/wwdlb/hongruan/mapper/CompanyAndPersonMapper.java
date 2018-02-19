package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.CompanyAndPerson;

public interface CompanyAndPersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyAndPerson record);

    int insertSelective(CompanyAndPerson record);

    CompanyAndPerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyAndPerson record);

    int updateByPrimaryKey(CompanyAndPerson record);
}