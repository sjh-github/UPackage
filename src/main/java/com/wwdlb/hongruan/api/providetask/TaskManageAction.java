package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.model.Task;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.TaskManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android项目管理
 */
@RestController
public class TaskManageAction {

    @Autowired
    private TaskManageServiceImpl taskManageServiceImpl;

    /**
     * 获取所有项目
     * @return 项目列表
     */
    @GetMapping(value = "/api/task")
    public ArrayList<Task> getAllTask() {
        return taskManageServiceImpl.getAllTask();
    }

    /**
     * 删除任务
     * @param taskID 任务ID
     * @return true/false
     */
    @DeleteMapping(value = "/api/task/{taskID}")
    public boolean deleteTask(@PathVariable Integer taskID) {
        return taskManageServiceImpl.deleteTask(taskID);
    }
}
