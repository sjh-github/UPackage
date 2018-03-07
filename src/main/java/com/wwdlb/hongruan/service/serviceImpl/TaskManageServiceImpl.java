package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.TaskMapper;
import com.wwdlb.hongruan.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 任务管理服务实例
 */
@Service
public class TaskManageServiceImpl {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 获取所有项目
     * @return 项目列表
     */
    public ArrayList<Task> getAllTask() {
        return taskMapper.getAllTask();
    }

}
