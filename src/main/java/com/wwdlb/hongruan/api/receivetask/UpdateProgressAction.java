package com.wwdlb.hongruan.api.receivetask;

import com.wwdlb.hongruan.pojo.CustomProgressIDAndResult;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.UpdateProgressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android更新小任务进度
 */
@RestController
@Transactional
public class UpdateProgressAction {

    @Autowired
    private UpdateProgressServiceImpl updateProgressServiceImpl;

    /**
     * 更新数量指标
     * @param smallTaskID 小任务ID
     * @param newNumberProgress 新的完成数量
     * @return true/false
     */
    @PostMapping(value = "/api/smallTaskNumberProgress")
    public boolean updateNumberProgress(Integer smallTaskID, Integer newNumberProgress) {
        return updateProgressServiceImpl.updateNumberProgress(smallTaskID, newNumberProgress);
    }

    /**
     * 更新自定义指标
     * @param customProgressIDAndResults 自定义指标ID及完成结果
     * @return true/false
     */
    @PostMapping(value = "/api/smallTaskCustomProgress")
    public boolean updateCustomProgrss(ArrayList<CustomProgressIDAndResult> customProgressIDAndResults) {
        return updateProgressServiceImpl.updateCustomProgress(customProgressIDAndResults);
    }
}
