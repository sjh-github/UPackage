package com.wwdlb.hongruan.service;


/**
 * 发布任务服务接口
 */
public interface ProvideTaskService {
    //新建外包任务
    boolean provideTask(String taskName, String taskDetail, int safetyGrade,
                      int priority, String startTime, String endTime);
}
