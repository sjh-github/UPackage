package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PersonnelAdministrator;

public interface PersonnelAdministratorMapper {
    int deleteByPrimaryKey(String email);

    int insert(PersonnelAdministrator record);

    int insertSelective(PersonnelAdministrator record);

    PersonnelAdministrator selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(PersonnelAdministrator record);

    int updateByPrimaryKeyWithBLOBs(PersonnelAdministrator record);

    int updateByPrimaryKey(PersonnelAdministrator record);
}