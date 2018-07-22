package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.pojo.ReceivePersonAndSmallTaskNumPojo;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ReceiveSmallTaskPersonManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android接包人员管理
 */
@RestController
public class ReceiveSmallTaskPersonManageAction {

    @Autowired
    private ReceiveSmallTaskPersonManageServiceImpl receiveSmallTaskPersonManageServiceImpl;

    /**
     * 人员审视
     * @return 接包人员列表及其正在进行的小任务数量
     */
    @GetMapping(value = "/api/receiveSmallTask")
    public ArrayList<ReceivePersonAndSmallTaskNumPojo> receiveSmallTaskSurvey() {
        return receiveSmallTaskPersonManageServiceImpl.receiveSmallTaskSurvey();
    }
}
