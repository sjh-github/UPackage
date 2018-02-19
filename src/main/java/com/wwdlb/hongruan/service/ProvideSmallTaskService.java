package com.wwdlb.hongruan.service;


import java.util.ArrayList;

/**
 * 发布小任务服务
 */
public interface ProvideSmallTaskService {
    //发布小任务
    boolean provideSmallTask(String taskName, String smallTaskName, String smallTaskDetail,
                             String receiveSmallTaskEmail, Integer numberProgress, ArrayList<String> customProgressArrayList);
}
