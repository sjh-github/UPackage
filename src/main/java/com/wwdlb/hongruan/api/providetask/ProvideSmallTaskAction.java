package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.service.serviceImpl.ProvideSmallTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

/**
 * Android发布小任务
 */
@RestController
public class ProvideSmallTaskAction {

    @Autowired
    private ProvideSmallTaskServiceImpl provideSmallTaskServiceImpl;

    /**
     * 发布外包小任务
     * @param taskName 任务名称
     * @param smallTaskName 小任务名称
     * @param smallTaskDetail 小任务详情
     * @param receiveSmallTaskEmail 指定接包人邮箱
     * @param numberProgress 数量指标（可空）
     * @param customProgressArrayList 自定义指标ArrayList（可空,按照第一步，第二步...顺序）
     * @return true:发布成功
     */
    @PostMapping(value = "/api/smallTask")
    public boolean provideSmallTask(@RequestParam String taskName, @RequestParam String smallTaskName,
                                    @RequestParam String smallTaskDetail, @RequestParam String receiveSmallTaskEmail,
                                    @RequestParam(required = false) Integer numberProgress,
                                    @RequestParam(required = false) ArrayList<String> customProgressArrayList) {
        return provideSmallTaskServiceImpl.provideSmallTask(taskName, smallTaskName, smallTaskDetail, receiveSmallTaskEmail, numberProgress, customProgressArrayList);
    }
}
