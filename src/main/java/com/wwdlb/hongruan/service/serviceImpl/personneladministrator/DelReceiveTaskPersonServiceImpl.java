package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 删除接包人服务实例
 */
@Service
public class DelReceiveTaskPersonServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    public boolean delReceiveTaskPerson(String email) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal != null) {
            receiveTask_personalMapper.deleteByPrimaryKey(email);
            return true;
        } else {
            return false;
        }
    }
}
