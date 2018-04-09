package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskAndSmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
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
}
