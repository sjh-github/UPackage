package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.*;
import com.wwdlb.hongruan.model.*;
import com.wwdlb.hongruan.service.ProvideSmallTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发布小任务服务实例
 */
@Service
public class ProvideSmallTaskServiceImpl /*implements ProvideSmallTaskService*/ {

    private Logger logger = LoggerFactory.getLogger(String.valueOf(ProvideSmallTaskServiceImpl.this));

    @Autowired
    private TaskAndSmallTaskMapper taskAndSmallTaskMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

    @Autowired
    private SmallTaskAndNumberProgressMapper smallTaskAndNumberProgressMapper;

    @Autowired
    private CustomProgressMapper customProgressMapper;

    @Autowired
    private SmallTaskAndCustomProgressMapper smallTaskAndCustomProgressMapper;

    /**
     * 发布外包小任务
     * @param taskName 任务名称
     * @param smallTaskName 小任务名称
     * @param smallTaskDetail 小任务详情
     * @param endTime 截止日期
     * @param receiveSmallTaskEmail 指定接包人邮箱
     * @param numberProgress 数量指标（可空）
     * @param customProgressArrayList 自定义指标ArrayList（可空,按照第一步，第二步...顺序）
     * @return true:发布成功
     */
    @Transactional
    //@Override
    public boolean provideSmallTask(String taskName, String smallTaskName, String smallTaskDetail, String endTime, String receiveSmallTaskEmail,
                                    Integer numberProgress, ArrayList<String> customProgressArrayList) {
        int taskID = taskMapper.selectIDByTaskName(taskName);
        if (taskID <= 0) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //新建小任务
        SmallTask smallTask = new SmallTask(smallTaskName, smallTaskDetail, endTime, "F", simpleDateFormat.format(date));
        smallTaskMapper.insert(smallTask);
        //创建任务与小任务关联
        int smallTaskID = smallTask.getSmalltaskid();
        TaskAndSmallTask taskAndSmallTask = new TaskAndSmallTask(taskID, smallTaskID);
        taskAndSmallTaskMapper.insert(taskAndSmallTask);
        //创建外包人员与小任务关联
        PersonAndSmallTask personAndSmallTask = new PersonAndSmallTask(receiveSmallTaskEmail, smallTaskID);
        personAndSmallTaskMapper.insert(personAndSmallTask);
        //创建小任务与数量指标关联
        if (numberProgress != null) {
            if (numberProgress <= 0) {
                logger.error("numberProgress should be bigger than 0");
                throw new RuntimeException("numberProgress should be bigger than 0");
            }
            SmallTaskAndNumberProgress smallTaskAndNumberProgress = new SmallTaskAndNumberProgress(smallTaskID,
                    numberProgress, 0, simpleDateFormat.format(date));
            smallTaskAndNumberProgressMapper.insert(smallTaskAndNumberProgress);
        }
        //创建自定义指标项
        if (customProgressArrayList != null) {
            for (int i = 0; i < customProgressArrayList.size(); i++) {
                CustomProgress customProgress = new CustomProgress(customProgressArrayList.get(i), "F", simpleDateFormat.format(date));
                customProgressMapper.insert(customProgress);
                //创建小任务与自定义指标关联
                int customProgressID = customProgress.getCustomprogressid();
                SmallTaskAndCustomProgress smallTaskAndCustomProgress = new SmallTaskAndCustomProgress(smallTaskID, customProgressID, (i + 1));
                smallTaskAndCustomProgressMapper.insert(smallTaskAndCustomProgress);
            }
        }
        return true;
    }
}
