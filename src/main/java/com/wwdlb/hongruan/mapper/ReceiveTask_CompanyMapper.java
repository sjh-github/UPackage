package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.ReceiveTask_Company;

public interface ReceiveTask_CompanyMapper {
    int deleteByPrimaryKey(Integer companyid);

    int insert(ReceiveTask_Company record);

    int insertSelective(ReceiveTask_Company record);

    ReceiveTask_Company selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(ReceiveTask_Company record);

    int updateByPrimaryKey(ReceiveTask_Company record);
}