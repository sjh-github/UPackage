package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.TaskAndSmallTask;

import java.util.ArrayList;

public interface TaskAndSmallTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskAndSmallTask record);

    int insertSelective(TaskAndSmallTask record);

    TaskAndSmallTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskAndSmallTask record);

    int updateByPrimaryKey(TaskAndSmallTask record);

    ArrayList<Integer> getSmallTaskIDByTaskID(int taskID);

    Integer getTaskIDBySmallTaskID(int smallTaskID);
}