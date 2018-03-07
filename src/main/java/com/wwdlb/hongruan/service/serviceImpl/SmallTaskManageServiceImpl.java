package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskAndSmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
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
    public ArrayList<SmallTask> getAllSmallTaskByTaskID(int taskID) {
        ArrayList<Integer> smallTaskIDList = taskAndSmallTaskMapper.getSmallTaskIDByTaskID(taskID);
        if (smallTaskIDList == null) {
            return null;
        }
        ArrayList<SmallTask> smallTaskArrayList = new ArrayList<>();
        for (int smallTaskID : smallTaskIDList) {
            smallTaskArrayList.add(smallTaskMapper.selectByPrimaryKey(smallTaskID));
        }
        return smallTaskArrayList;
    }
}
