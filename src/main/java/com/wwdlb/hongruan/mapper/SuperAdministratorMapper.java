package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SuperAdministrator;

public interface SuperAdministratorMapper {
    int deleteByPrimaryKey(String email);

    int insert(SuperAdministrator record);

    int insertSelective(SuperAdministrator record);

    SuperAdministrator selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(SuperAdministrator record);

    int updateByPrimaryKeyWithBLOBs(SuperAdministrator record);

    int updateByPrimaryKey(SuperAdministrator record);
}