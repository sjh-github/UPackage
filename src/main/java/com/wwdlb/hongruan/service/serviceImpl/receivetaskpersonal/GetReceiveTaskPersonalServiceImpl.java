package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取接包人信息
 */
@Service
public class GetReceiveTaskPersonalServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 获取接包人信息
     * @param email 接包人邮箱
     * @return 接包人
     */
    public ReceiveTask_Personal getReceiveTaskPersonalByEmail(String email) {
        return receiveTask_personalMapper.selectByPrimaryKey(email);
    }
}
