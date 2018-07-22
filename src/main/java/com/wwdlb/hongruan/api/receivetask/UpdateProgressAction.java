package com.wwdlb.hongruan.api.receivetask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wwdlb.hongruan.pojo.CustomProgressIDAndResultPojo;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.UpdateProgressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public boolean updateNumberProgress(@RequestParam Integer smallTaskID, @RequestParam Integer newNumberProgress) {
        return updateProgressServiceImpl.updateNumberProgress(smallTaskID, newNumberProgress);
    }

    /**
     * 更新自定义指标
     * @return true/false
     */
    @PostMapping(value = "/api/smallTaskCustomProgress")
    public boolean updateCustomProgrss(@RequestParam String customProgressIDAndResults) {
        JSONArray jsonArray = JSONArray.parseArray(customProgressIDAndResults);
        if (jsonArray == null || jsonArray.size() < 1) {
            return false;
        }
        ArrayList<CustomProgressIDAndResultPojo> customProgressIDAndResultArrayList = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            CustomProgressIDAndResultPojo customProgressIDAndResult =
                    new CustomProgressIDAndResultPojo(jsonObject.getInteger("customProgressID"), jsonObject.getBoolean("haveFinished"));
            customProgressIDAndResultArrayList.add(customProgressIDAndResult);
        }
        return updateProgressServiceImpl.updateCustomProgress(customProgressIDAndResultArrayList);
    }
}
