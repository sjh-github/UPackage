package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.TaskAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskMapper;
import com.wwdlb.hongruan.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小任务与任务关联服务实例
 */
@Service
public class SmallTaskAndTaskServiceImpl {
   @Autowired
    private TaskAndSmallTaskMapper taskAndSmallTaskMapper;

   @Autowired
    private TaskMapper taskMapper;

    /**
     * 获取任务详情
     * @param smallTaskID 小任务ID
     * @return 任务/NULL
     */
   public Task getTaskBySmallTaskID(Integer smallTaskID) {
       Integer taskID = taskAndSmallTaskMapper.getTaskIDBySmallTaskID(smallTaskID);
       if (taskID != null) {
           Task task = taskMapper.selectByPrimaryKey(taskID);
           return task;
       }
       return null;
   }
}
