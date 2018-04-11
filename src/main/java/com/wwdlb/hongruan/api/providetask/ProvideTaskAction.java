package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ProvideTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android发布任务
 */
@RestController
public class ProvideTaskAction {

    @Autowired
    private ProvideTaskServiceImpl provideTaskServiceImpl;

    /**
     * 新建外包任务
     * @param taskName 任务名称
     * @param taskDetail 任务详情描述
     * @param safetyGrade 安全等级
     * @param priority 优先级
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return taskID/NULL
     */
    @PostMapping(value = "/api/task")
    public Integer provideTask(@RequestParam String taskName, @RequestParam(required = false) String taskDetail, @RequestParam int safetyGrade,
                            @RequestParam int priority, @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
        return provideTaskServiceImpl.provideTask(taskName, taskDetail, safetyGrade, priority, startTime, endTime);
    }
}
