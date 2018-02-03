package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PermissionAndPersonAndTask;

public interface PermissionAndPersonAndTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionAndPersonAndTask record);

    int insertSelective(PermissionAndPersonAndTask record);

    PermissionAndPersonAndTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionAndPersonAndTask record);

    int updateByPrimaryKey(PermissionAndPersonAndTask record);
}