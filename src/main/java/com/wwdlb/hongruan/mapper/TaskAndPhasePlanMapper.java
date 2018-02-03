package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.TaskAndPhasePlan;

public interface TaskAndPhasePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskAndPhasePlan record);

    int insertSelective(TaskAndPhasePlan record);

    TaskAndPhasePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskAndPhasePlan record);

    int updateByPrimaryKey(TaskAndPhasePlan record);
}