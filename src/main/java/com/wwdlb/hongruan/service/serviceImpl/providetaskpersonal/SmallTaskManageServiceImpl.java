package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.*;
import com.wwdlb.hongruan.model.*;
import com.wwdlb.hongruan.pojo.SmallTaskPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 小任务管理服务实例
 */
@Service
public class SmallTaskManageServiceImpl {
    @Autowired
    private TaskAndSmallTaskMapper taskAndSmallTaskMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    @Autowired
    private SmallTaskAndNumberProgressMapper smallTaskAndNumberProgressMapper;

    @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

    @Autowired
    private SmallTaskAndCustomProgressMapper smallTaskAndCustomProgressMapper;

    @Autowired
    private CustomProgressMapper customProgressMapper;

    @Autowired
    private SmallTaskAndProvidePersonEmailMapper smallTaskAndProvidePersonEmailMapper;


    /**
     * 获取项目下属所有小任务
     * @param taskID 项目ID
     * @return 小任务列表/NULL
     */
    public ArrayList<SmallTaskPojo> getAllSmallTaskByTaskID(int taskID) {
        ArrayList<Integer> smallTaskIDList = taskAndSmallTaskMapper.getSmallTaskIDByTaskID(taskID);
        if (smallTaskIDList == null) {
            return null;
        }
        ArrayList<SmallTask> smallTaskArrayList = new ArrayList<>();
        for (int smallTaskID : smallTaskIDList) {
            smallTaskArrayList.add(smallTaskMapper.selectByPrimaryKey(smallTaskID));
        }
        ArrayList<SmallTaskPojo> smallTaskPojos = new ArrayList<>(smallTaskArrayList.size());
        SmallTaskPojo smallTaskPojo = new SmallTaskPojo();
        for (SmallTask smallTask : smallTaskArrayList) {
            smallTaskPojo.setTaskid(taskID);
            smallTaskPojo.setSmalltaskid(smallTask.getSmalltaskid());
            smallTaskPojo.setSmalltaskname(smallTask.getSmalltaskname());
            smallTaskPojo.setSmalltaskdetail(smallTask.getSmalltaskdetail());
            smallTaskPojo.setEndtime(smallTask.getEndtime());
            smallTaskPojo.setHavefinished(smallTask.getHavefinished());
            smallTaskPojo.setFinishtime(smallTask.getFinishtime());
            smallTaskPojos.add(smallTaskPojo);
        }
        return smallTaskPojos;
    }

    /**
     * 删除小任务
     * @param smallTaskID 小任务ID
     * @return true/false
     */
    public boolean deleteSmallTask(String email, Integer smallTaskID) {
        if (smallTaskID == null || smallTaskID < 1) {
            return false;
        }
        SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
        if (smallTask == null) {
            return false;
        }
        //删除小任务
        smallTaskMapper.deleteByPrimaryKey(smallTaskID);
        SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndNumberProgressMapper.selectBySmallTaskID(smallTaskID);
        if (smallTaskAndNumberProgress != null) {
            Integer smallTaskAndNumerProgressId = smallTaskAndNumberProgress.getId();
            //删除数量指标小任务关联
            smallTaskAndNumberProgressMapper.deleteByPrimaryKey(smallTaskAndNumerProgressId);
        }
        PersonAndSmallTask personAndSmallTask = personAndSmallTaskMapper.selectBySmallTaskID(smallTaskID);
        if (personAndSmallTask != null) {
            Integer personAndSmallTaskID = personAndSmallTask.getId();
            //删除接包人与小任务关联
            personAndSmallTaskMapper.deleteByPrimaryKey(personAndSmallTaskID);
        }
        TaskAndSmallTask taskAndSmallTask = taskAndSmallTaskMapper.selectBySmallTaskID(smallTaskID);
        if (taskAndSmallTask != null) {
            Integer taskAndSmallTaskID = taskAndSmallTask.getId();
            //删除任务与小任务关联
            taskAndSmallTaskMapper.deleteByPrimaryKey(taskAndSmallTaskID);
        }
        ArrayList<SmallTaskAndCustomProgress> smallTaskAndCustomProgressList = smallTaskAndCustomProgressMapper.getSmallTaskAndCustomProgressBySmallTaskID(smallTaskID);
        if (smallTaskAndCustomProgressList != null) {
            for (SmallTaskAndCustomProgress smallTaskAndCustomProgress : smallTaskAndCustomProgressList) {
                Integer smallTaskAndCustomProgressID = smallTaskAndCustomProgress.getId();
                //删除小任务与自定义指标关联
                smallTaskAndCustomProgressMapper.deleteByPrimaryKey(smallTaskAndCustomProgressID);
                Integer customProgressID = smallTaskAndCustomProgress.getCustomprogressid();
                CustomProgress customProgress = customProgressMapper.selectByPrimaryKey(customProgressID);
                if (customProgress != null) {
                    //删除自定义指标
                    customProgressMapper.deleteByPrimaryKey(customProgressID);
                }
            }
        }
        //删除小任务与发包者关联
        smallTaskAndProvidePersonEmailMapper.deleteByPrimaryKey(email);
        return true;
    }
}
