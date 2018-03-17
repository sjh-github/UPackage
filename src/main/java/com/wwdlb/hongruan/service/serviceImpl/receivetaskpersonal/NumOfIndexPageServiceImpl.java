package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.ReceiveTask_CompanyMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NumOfIndexPageServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private ReceiveTask_CompanyMapper receiveTask_companyMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 获取接包人员个数
     * @return 接包人员个数
     */
    public int getNumOfReceiveTaskPersonal() {
        return receiveTask_personalMapper.getNumOfAll();
    }

    /**
     * 获取接包公司个数
     * @return 接包公司个数
     */
    public int getNumOfReceiveTaskCompany() {
        return receiveTask_companyMapper.getNumOfAll();
    }

    /**
     * 获取已完成小任务个数
     * @return 已完成小任务个数
     */
    public int getNumOfFinishedSmallTask() {
        return smallTaskMapper.getNumOfSmallTaskByState("T");
    }

    /**
     * 获取所有已发布小任务个数
     * @return 已发布小任务个数
     */
    public int getNumOfSmallTask() {
        return smallTaskMapper.getNumOfSmallTaskByState("T") + smallTaskMapper.getNumOfSmallTaskByState("F");
    }
}
