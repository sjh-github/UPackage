package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android小任务管理
 */
@RestController
public class SmallTaskManageAction {

    @Autowired
    private SmallTaskManageServiceImpl smallTaskManageServiceImpl;

    /**
     * 获取项目下属所有小任务
     * @param taskID 项目ID
     * @return 小任务列表/NULL
     */
    @GetMapping(value = "/api/smalltask")
    public ArrayList<SmallTask> getAllSmallTaskByTaskID(@RequestParam int taskID) {
        return smallTaskManageServiceImpl.getAllSmallTaskByTaskID(taskID);
    }
}
