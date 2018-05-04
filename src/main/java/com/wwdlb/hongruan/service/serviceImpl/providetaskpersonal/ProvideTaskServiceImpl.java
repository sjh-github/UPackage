package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.TaskMapper;
import com.wwdlb.hongruan.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发布任务服务接口实例
 */
@Service
public class ProvideTaskServiceImpl {

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
     * @return taskID/NULL
     */
    @Transactional
    public Integer provideTask(String taskName, String taskDetail, int safetyGrade, int priority,
                            String startTime, String endTime) {
        if (safetyGrade <= 0) {
            return null;
        }
        if (priority <= 0) {
            return null;
        }

        Integer taskID = taskMapper.selectIDByTaskName(taskName);
        if (taskID != null) {
            return null;
        }
        if (startTime == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            startTime = simpleDateFormat.format(date);
        }

        Task task = new Task(taskName, taskDetail, safetyGrade, priority, startTime, endTime);
        task.setProgress(0.0);
        taskMapper.insert(task);
        taskID = task.getTaskid();
        return taskID;
    }
}
