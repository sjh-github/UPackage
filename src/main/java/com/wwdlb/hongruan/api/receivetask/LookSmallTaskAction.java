package com.wwdlb.hongruan.api.receivetask;


import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.LookSmallTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android接包方查看小任务
 */
@RestController
public class LookSmallTaskAction {

    @Autowired
    private LookSmallTaskServiceImpl lookSmallTaskServiceImpl;


    /**
     * 根据接包人邮箱查找所有小任务
     * @param email 接包人邮箱
     * @return null:无小任务，非null:小任务列表
     */
    @GetMapping(value = "/api/smallTask")
    public ArrayList<SmallTask> lookAllSmallTaskByEmail(@RequestParam String email) {
        return lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
    }

}
