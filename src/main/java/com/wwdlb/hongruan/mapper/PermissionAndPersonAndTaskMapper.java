package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PermissionAndPersonAndTask;

import java.util.ArrayList;

public interface PermissionAndPersonAndTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionAndPersonAndTask record);

    int insertSelective(PermissionAndPersonAndTask record);

    PermissionAndPersonAndTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionAndPersonAndTask record);

    int updateByPrimaryKey(PermissionAndPersonAndTask record);

    ArrayList<PermissionAndPersonAndTask> selectByEmail(String email);
}