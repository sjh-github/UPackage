package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.pojo.ReceivePersonAndSmallTaskNumPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 接包人员管理服务实例
 */
@Service
public class ReceiveSmallTaskPersonManageServiceImpl {

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

    /**
     * 人员审视
     * @return 接包人员列表及其小任务数量
     */
    public ArrayList<ReceivePersonAndSmallTaskNumPojo> receiveSmallTaskSurvey() {
        List<ReceiveTask_Personal> receiveTaskPersonalList = receiveTask_personalMapper.selectAll();
        if (receiveTaskPersonalList == null) {
            return null;
        }
        ArrayList<ReceivePersonAndSmallTaskNumPojo> receivePersonAndSmallTaskNumPojoArrayList = new ArrayList<>();
        for (ReceiveTask_Personal receiveTask_personal : receiveTaskPersonalList) {
            ReceivePersonAndSmallTaskNumPojo receivePersonAndSmallTaskNumPojo = new ReceivePersonAndSmallTaskNumPojo();
            receivePersonAndSmallTaskNumPojo.setEmail(receiveTask_personal.getEmail());
            receivePersonAndSmallTaskNumPojo.setName(receiveTask_personal.getName());
            int num = personAndSmallTaskMapper.selectSmallTaskIDByEmail(receiveTask_personal.getEmail()).size();
            receivePersonAndSmallTaskNumPojo.setNum(num);
            receivePersonAndSmallTaskNumPojoArrayList.add(receivePersonAndSmallTaskNumPojo);
        }
        return receivePersonAndSmallTaskNumPojoArrayList;
    }

}
