package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
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

    /**
     * 根据接包人邮箱查找所有小任务
     * @param email 接包人邮箱
     * @return 小任务列表
     */
    public ArrayList<SmallTask> findAllSmallTaskByEmail(String email) {
        ArrayList<Integer> smallTaskIDArrayList = personAndSmallTaskMapper.selectSmallTaskIDByEmail(email);
        ArrayList<SmallTask> smallTaskArrayList = new ArrayList<>();
        for (Integer smallTaskID : smallTaskIDArrayList) {
            smallTaskArrayList.add(smallTaskMapper.selectByPrimaryKey(smallTaskID));
        }
        return smallTaskArrayList;
    }
}
