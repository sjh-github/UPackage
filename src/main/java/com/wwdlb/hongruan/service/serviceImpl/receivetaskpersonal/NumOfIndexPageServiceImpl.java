package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_CompanyMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class NumOfIndexPageServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private ReceiveTask_CompanyMapper receiveTask_companyMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

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
     * 获取指定接包人已完成小任务个数
     * @return 已完成小任务个数
     */
    public int getNumOfFinishedSmallTaskByEmail(String email) {
        ArrayList<Integer> smallTaskIDs = personAndSmallTaskMapper.selectSmallTaskIDByEmail(email);
        if (smallTaskIDs == null) {
            return 0;
        }
        SmallTask smallTask = null;
        int numOfFinishedSmallTaskByEmail = 0;
        for (Integer smallTaskID : smallTaskIDs) {
            smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
            if (smallTask != null) {
                if (smallTask.getHavefinished().equals('T')) {
                    numOfFinishedSmallTaskByEmail++;
                }
            }
        }
        return numOfFinishedSmallTaskByEmail;
    }

    /**
     * 获取指定接包人已完成小任务个数
     * @return 已完成小任务个数
     */
    public int getNumOfFinishedSmallTask(ArrayList<SmallTask> smallTasks) {
       if (smallTasks == null) {
           return 0;
       }
       int numOfFinishedSmallTask = 0;
       for (SmallTask smallTask : smallTasks) {
           if (smallTask.getHavefinished().equals("T")) {
               numOfFinishedSmallTask++;
           }
       }
       return numOfFinishedSmallTask;
    }

    /**
     * 获取所有已发布小任务个数
     * @return 已发布小任务个数
     */
    public int getNumOfSmallTask() {
        return smallTaskMapper.getNumOfSmallTaskByState("T") + smallTaskMapper.getNumOfSmallTaskByState("F");
    }

}
