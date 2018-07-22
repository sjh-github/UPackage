package com.wwdlb.hongruan.api;

import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndProgressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android获取小任务进度
 */
@RestController
public class GetSmallTaskProgressAction {
    @Autowired
    private SmallTaskAndProgressServiceImpl smallTaskAndProgressServiceImpl;

    /**
     * 获取小任务进度
     * @param smallTaskID 小任务进度
     * @return 百分制Double/NULL
     */
    @GetMapping(value = "/api/smallTask/progress")
    public Integer getSmallTaskProgress(Integer smallTaskID) {
        if (smallTaskID != null) {
            return smallTaskAndProgressServiceImpl.getProgressBySmallTaskID(smallTaskID);
        }
        return null;
    }
}
