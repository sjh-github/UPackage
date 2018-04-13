package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.model.Task;
import com.wwdlb.hongruan.model.TaskAndSmallTask;
import com.wwdlb.hongruan.pojo.SmallTaskAndEmailPojo;
import com.wwdlb.hongruan.pojo.SmallTaskDetailAndProgressPojo;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndProgressServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 接包方查看小任务服务实例
 */
@Service
public class LookSmallTaskServiceImpl {

    @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    @Autowired
    private TaskAndSmallTaskMapper taskAndSmallTaskMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private SmallTaskAndProgressServiceImpl smallTaskAndProgressServiceImpl;

    /**
     * 根据接包人邮箱查找所有小任务
     * @param email 接包人邮箱
     * @return null:无小任务，非null:小任务列表
     */
    public ArrayList<SmallTaskAndEmailPojo> findAllSmallTaskAndEmailPojoByEmail(String email) {
        ArrayList<Integer> smallTaskIDArrayList = personAndSmallTaskMapper.selectSmallTaskIDByEmail(email);
        if (smallTaskIDArrayList == null) {
            return null;
        }
        ArrayList<SmallTaskAndEmailPojo> smallTaskAndEmailPojos = new ArrayList<>(smallTaskIDArrayList.size());
        for (Integer smallTaskID : smallTaskIDArrayList) {
            smallTaskAndEmailPojos.add(new SmallTaskAndEmailPojo(smallTaskMapper.selectByPrimaryKey(smallTaskID), email));
        }
        return smallTaskAndEmailPojos;
    }

    /**
     * 根据接包人邮箱查找所有小任务
     * @param email 接包人邮箱
     * @return null:无小任务，非null:小任务列表
     */
    public ArrayList<SmallTask> findAllSmallTaskByEmail(String email) {
        ArrayList<Integer> smallTaskIDArrayList = personAndSmallTaskMapper.selectSmallTaskIDByEmail(email);
        if (smallTaskIDArrayList == null) {
            return null;
        }
        ArrayList<SmallTask> smallTasks = new ArrayList<>(smallTaskIDArrayList.size());
        SmallTask smallTask = null;
        for (Integer smallTaskID : smallTaskIDArrayList) {
            smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
            if (smallTask != null) {
                smallTasks.add(smallTask);
            }
        }
        return smallTasks;
    }

    /**
     * 获取小任务详情及安全级优先级进度
     * @param email 接包人邮箱
     * @return 小任务详情及安全级优先级进度
     */
    public ArrayList<SmallTaskDetailAndProgressPojo> getSmallTaskDetailAndProgressPojoByEmail(String email) {
        //获取接包人对应小任务ID列表
        ArrayList<Integer> smallTaskIDArrayList = personAndSmallTaskMapper.selectSmallTaskIDByEmail(email);
        if (smallTaskIDArrayList == null) {
            return null;
        }
        Task task = null;
        SmallTask smallTask = null;
        TaskAndSmallTask taskAndSmallTask = null;
        ArrayList<SmallTaskDetailAndProgressPojo> smallTaskDetailAndProgressPojos = new ArrayList<>(smallTaskIDArrayList.size());
        SmallTaskDetailAndProgressPojo smallTaskDetailAndProgressPojo = null;
        for (Integer smallTaskID : smallTaskIDArrayList) {
            smallTaskDetailAndProgressPojo = new SmallTaskDetailAndProgressPojo();
            smallTask =smallTaskMapper.selectByPrimaryKey(smallTaskID);
            if (smallTask != null) {
                smallTaskDetailAndProgressPojo.setSmallTask(smallTask);
                taskAndSmallTask = taskAndSmallTaskMapper.selectBySmallTaskID(smallTaskID);
                if (taskAndSmallTask != null) {
                    task = taskMapper.selectByPrimaryKey(taskAndSmallTask.getTaskid());
                    if (task != null) {
                        smallTaskDetailAndProgressPojo.setSafetyGrade(task.getSafetygrade());
                        smallTaskDetailAndProgressPojo.setPriority(task.getPriority());
                        Integer progress = smallTaskAndProgressServiceImpl.getProgressBySmallTaskID(smallTaskID);
                        if (progress != null) {
                            smallTaskDetailAndProgressPojo.setProgress(progress);
                            smallTaskDetailAndProgressPojos.add(smallTaskDetailAndProgressPojo);
                        }
                    }
                }
            }
        }
        return smallTaskDetailAndProgressPojos;
    }

    /**
     * 查找正在进行小任务
     * @param smallTasks 小任务列表
     * @return null:无小任务，非null:小任务列表
     */
    public ArrayList<SmallTask> findRunningSmallTask(ArrayList<SmallTask> smallTasks) {
       if (smallTasks == null) {
           return null;
       }
       ArrayList<SmallTask> runningSmallTasks = new ArrayList<>();
       for (SmallTask smallTask : smallTasks) {
           if (smallTask.getHavefinished().equals("F")) {
               runningSmallTasks.add(smallTask);
           }
       }
       return runningSmallTasks;
    }

    /**
     * 查找已完成小任务
     * @param smallTasks 小任务列表
     * @return null:无小任务，非null:小任务列表
     */
    public ArrayList<SmallTask> findFinishedSmallTask(ArrayList<SmallTask> smallTasks) {
        if (smallTasks == null) {
            return null;
        }
        ArrayList<SmallTask> runningSmallTasks = new ArrayList<>();
        for (SmallTask smallTask : smallTasks) {
            if (smallTask.getHavefinished().equals("T")) {
                runningSmallTasks.add(smallTask);
            }
        }
        return runningSmallTasks;
    }
}
