package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.Task;

import java.util.ArrayList;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskid);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskid);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    Integer selectIDByTaskName(String taskName);

    ArrayList<Task> getAllTask();
}