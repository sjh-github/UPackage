package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.ProvideTask_Personal;

public interface ProvideTask_PersonalMapper {
    int deleteByPrimaryKey(String email);

    int insert(ProvideTask_Personal record);

    int insertSelective(ProvideTask_Personal record);

    ProvideTask_Personal selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(ProvideTask_Personal record);

    int updateByPrimaryKeyWithBLOBs(ProvideTask_Personal record);

    int updateByPrimaryKey(ProvideTask_Personal record);
}