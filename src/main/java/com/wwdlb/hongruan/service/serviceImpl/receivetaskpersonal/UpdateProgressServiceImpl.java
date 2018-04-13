package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.*;
import com.wwdlb.hongruan.model.*;
import com.wwdlb.hongruan.pojo.CustomProgressIDAndResultPojo;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndProgressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 更新小任务进度服务实例
 */
@Service
@Transactional
public class UpdateProgressServiceImpl {
    @Autowired
    private SmallTaskAndNumberProgressMapper smallTaskAndNumberProgressMapper;

    @Autowired
    private CustomProgressMapper customProgressMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    @Autowired
    private TaskAndSmallTaskMapper taskAndSmallTaskMapper;

    @Autowired
    private SmallTaskAndProgressServiceImpl smallTaskAndProgressServiceImpl;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private SmallTaskAndCustomProgressMapper smallTaskAndCustomProgressMapper;

    /**
     * 更新任务进度
     * @param smallTaskID 小任务ID
     * @param newNumberProgress 完成数量
     * @return true/false
     */
    public boolean updateNumberProgress(Integer smallTaskID, Integer newNumberProgress) {
        SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndNumberProgressMapper.selectBySmallTaskID(smallTaskID);
        if (smallTaskAndNumberProgress == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        smallTaskAndNumberProgress.setFinishednumber(newNumberProgress);
        //完成指标
        if (newNumberProgress >= smallTaskAndNumberProgress.getNumberprogress()) {
            SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
            if (smallTask != null) {
                smallTask.setHavefinished("T");
                smallTask.setFinishtime(simpleDateFormat.format(date));
                smallTaskMapper.updateByPrimaryKeySelective(smallTask);
            }
            //更新项目进度
            double taskProgress = 0.0;
            TaskAndSmallTask taskAndSmallTask = taskAndSmallTaskMapper.selectBySmallTaskID(smallTaskID);
            if (taskAndSmallTask != null) {
                Integer taskID = taskAndSmallTask.getTaskid();
                if (taskID != null) {
                    ArrayList<Integer> smallTaskIDList = taskAndSmallTaskMapper.getSmallTaskIDByTaskID(taskID);
                    if (smallTaskIDList != null) {
                        //该项目对应小任务个数
                        Integer smallTaskNum = smallTaskIDList.size();
                        for (Integer smallTaskID1 : smallTaskIDList) {
                                //小任务进度
                                Integer smallTaskProgress = smallTaskAndProgressServiceImpl.getProgressBySmallTaskID(smallTaskID1);
                                if (smallTaskProgress != null) {
                                    taskProgress += (1.0 / smallTaskNum * smallTaskProgress);
                                }
                        }
                        Task task = new Task();
                        task.setTaskid(taskID);
                        task.setProgress(taskProgress);
                        taskMapper.updateByPrimaryKeySelective(task);
                    }
                }
            }
        }
        smallTaskAndNumberProgress.setUpdatetime(simpleDateFormat.format(date));
        smallTaskAndNumberProgressMapper.updateByPrimaryKeySelective(smallTaskAndNumberProgress);
        return true;
    }

    /**
     * 更新自定义指标完成情况
     * @param customProgressIDAndResults 自定义指标ID及完成结果（boolean）
     * @return true/false
     */
    public boolean updateCustomProgress(ArrayList<CustomProgressIDAndResultPojo> customProgressIDAndResults) {
        System.out.println(customProgressIDAndResults);
        if (customProgressIDAndResults == null || customProgressIDAndResults.size() < 1) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        for (CustomProgressIDAndResultPojo customProgressIDAndResult : customProgressIDAndResults) {
            //该指标已完成
            if (customProgressIDAndResult.isHaveFinihed()) {
                //获取自定义指标
                CustomProgress customProgress = customProgressMapper.selectByPrimaryKey(customProgressIDAndResult.getCustomProgressID());
                if (customProgress != null) {
                    if (customProgress.getHavefinished().equals("F")) {
                        customProgress.setHavefinished("T");
                        customProgress.setFinishtime(simpleDateFormat.format(date));
                        customProgressMapper.updateByPrimaryKeySelective(customProgress);
                    }
                }
            }
        }
        //更新项目进度
        double taskProgress = 0.0;
        Integer customID = customProgressIDAndResults.get(0).getCustomProgressID();
        SmallTaskAndCustomProgress smallTaskAndCustomProgress = smallTaskAndCustomProgressMapper.selectByCustomID(customID);
        if (smallTaskAndCustomProgress != null) {
            Integer smallTaskID = smallTaskAndCustomProgress.getSmalltaskid();
            if (smallTaskID != null) {
                TaskAndSmallTask taskAndSmallTask = taskAndSmallTaskMapper.selectBySmallTaskID(smallTaskID);
                if (taskAndSmallTask != null) {
                    Integer taskID = taskAndSmallTask.getTaskid();
                    if (taskID != null) {
                        ArrayList<Integer> smallTaskIDList = taskAndSmallTaskMapper.getSmallTaskIDByTaskID(taskID);
                        if (smallTaskIDList != null) {
                            //该项目对应小任务数量
                            Integer smallTaskNum = smallTaskIDList.size();
                            for (Integer smallTaskID1 : smallTaskIDList) {
                                //小任务进度
                                Integer smallTaskProgress = smallTaskAndProgressServiceImpl.getProgressBySmallTaskID(smallTaskID1);
                                if (smallTaskProgress != null) {
                                    taskProgress += (1.0 / smallTaskNum * smallTaskProgress);
                                }
                            }
                            Task task = new Task();
                            task.setProgress(taskProgress);
                            task.setTaskid(taskID);
                            taskMapper.updateByPrimaryKeySelective(task);
                        }
                    }
                }
            }
        }
        return true;
    }
}
