package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.SmallTaskAndProvidePersonEmailMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.model.SmallTaskAndProvidePersonEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 发包人任务显示
 */
@Service
public class ShowSmallTaskByProvidePersonEmailServiceImpl {
    @Autowired
    private SmallTaskAndProvidePersonEmailMapper smallTaskAndProvidePersonEmailMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     *根据发包人邮箱查找所有小任务
     *@param  email 发包人邮箱
     *@return 所有小任务列表/NULL
     */
    public ArrayList<SmallTask> getAllSmallTaskByProvidePersonEmail(String email) {
        ArrayList<SmallTaskAndProvidePersonEmail> smallTaskAndProvidePersonNameList = smallTaskAndProvidePersonEmailMapper.selectByProvidePersonEmail(email);
        if(smallTaskAndProvidePersonNameList != null) {
            ArrayList<SmallTask> smallTaskArrayList = new ArrayList<>();
            for(SmallTaskAndProvidePersonEmail smallTaskAndProvidePersonName : smallTaskAndProvidePersonNameList) {
                Integer smallTaskID = smallTaskAndProvidePersonName.getSmalltaskid();
                SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
                if(smallTask != null) {
                    smallTaskArrayList.add(smallTask);
                }
            }
            return smallTaskArrayList;
        }
        return null;
    }

    /**
     *根据发包人邮箱查找进行中小任务
     *@param  email 发包人邮箱
     *@return 进行中小任务列表/NULL
     */
    public ArrayList<SmallTask> getRunningSmallTaskByProvidePersonEmail(String email) {
        ArrayList<SmallTask> allSmallTask = getAllSmallTaskByProvidePersonEmail(email);
        if(allSmallTask == null) {
            return null;
        }
        ArrayList<SmallTask> runningSmallTaskList = new ArrayList<>();
        for(SmallTask smallTask : allSmallTask) {
            if(smallTask.getHavefinished().equals("F")) {
                runningSmallTaskList.add(smallTask);
            }
        }
        return runningSmallTaskList;
    }

    /**
     *根据发包人邮箱查找已完成小任务
     *@param email 发包人邮箱
     *@return 已完成小任务列表/NULL
     */
    public ArrayList<SmallTask> getFinishedSmallTaskByProvidePersonEmail(String email) {
        ArrayList<SmallTask> allSmallTask = getAllSmallTaskByProvidePersonEmail(email);
        if(allSmallTask == null) {
            return null;
        }
        ArrayList<SmallTask> finishedSmallTaskList = new ArrayList<>();
        for(SmallTask smallTask : allSmallTask) {
            if("T".equals(smallTask.getHavefinished())) {
                finishedSmallTaskList.add(smallTask);
            }
        }
        return finishedSmallTaskList;
    }
}
