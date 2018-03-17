package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.model.SmallTask;
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

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 人员审视
     * @return 接包人员列表及其正在进行的小任务数量/NULL:无接包人员列表
     */
    public ArrayList<ReceivePersonAndSmallTaskNumPojo> receiveSmallTaskSurvey() {
        List<ReceiveTask_Personal> receiveTaskPersonalList = receiveTask_personalMapper.selectAll();
        //暂无接包人员列表
        if (receiveTaskPersonalList == null) {
            return null;
        }
        ArrayList<ReceivePersonAndSmallTaskNumPojo> receivePersonAndSmallTaskNumPojoArrayList = new ArrayList<>();
        for (ReceiveTask_Personal receiveTask_personal : receiveTaskPersonalList) {
            //该接包人员对应的小任务ID列表
            ArrayList<Integer> smallTaskIDArrayList = personAndSmallTaskMapper.selectSmallTaskIDByEmail(receiveTask_personal.getEmail());
            //接包人员无对应的小任务
            if (smallTaskIDArrayList == null) {
                ReceivePersonAndSmallTaskNumPojo receivePersonAndSmallTaskNumPojo = new ReceivePersonAndSmallTaskNumPojo();
                receivePersonAndSmallTaskNumPojo.setEmail(receiveTask_personal.getEmail());
                receivePersonAndSmallTaskNumPojo.setName(receiveTask_personal.getName());
                receivePersonAndSmallTaskNumPojo.setNum(0);
                receivePersonAndSmallTaskNumPojoArrayList.add(receivePersonAndSmallTaskNumPojo);
            } else {
                int num = 0;
                for (int smallTaskID : smallTaskIDArrayList) {
                    //该小任务暂未完成
                    SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
                    if (smallTask == null) {
                        continue;
                    }
                    if (smallTask.getHavefinished().equals("F")) {
                        num++;
                    }
                }
                ReceivePersonAndSmallTaskNumPojo receivePersonAndSmallTaskNumPojo = new ReceivePersonAndSmallTaskNumPojo();
                receivePersonAndSmallTaskNumPojo.setEmail(receiveTask_personal.getEmail());
                receivePersonAndSmallTaskNumPojo.setName(receiveTask_personal.getName());
                receivePersonAndSmallTaskNumPojo.setNum(num);
                receivePersonAndSmallTaskNumPojoArrayList.add(receivePersonAndSmallTaskNumPojo);
            }
        }
        return receivePersonAndSmallTaskNumPojoArrayList;
    }
}
