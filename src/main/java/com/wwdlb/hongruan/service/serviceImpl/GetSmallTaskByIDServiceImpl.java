package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取小任务详情服务实例
 */
@Service
public class GetSmallTaskByIDServiceImpl {
    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 获取小任务
     * @param smallTaskID 小任务ID
     * @return 小任务/NULL
     */
    public SmallTask getSmallTaskByID(int smallTaskID) {
        SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
        return smallTask;
    }
}
