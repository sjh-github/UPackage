package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.TaskAndSmallTask;

public interface TaskAndSmallTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskAndSmallTask record);

    int insertSelective(TaskAndSmallTask record);

    TaskAndSmallTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskAndSmallTask record);

    int updateByPrimaryKey(TaskAndSmallTask record);
}