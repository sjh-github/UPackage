package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.TaskMapper;
import com.wwdlb.hongruan.model.Task;
import com.wwdlb.hongruan.service.ProvideTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 发布任务服务接口实例
 */
@Service
public class ProvideTaskServiceImpl/* implements ProvideTaskService*/ {
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 新建外包任务
     * @param taskName 任务名称
     * @param taskDetail 任务详情描述
     * @param safetyGrade 安全等级
     * @param priority 优先级
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return true:新建成功
     */
    //@Override
    @Transactional
    public boolean provideTask(String taskName, String taskDetail, int safetyGrade, int priority,
                            String startTime, String endTime) {
        Task task = new Task(taskName, taskDetail, safetyGrade, priority, startTime, endTime);
        taskMapper.insert(task);
        return true;
    }
}
