package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetNameByEmailServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    public String getReceiveTaskPersonalNameByEmail(String email) {
        if (email != null) {
            ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
            if (receiveTask_personal != null) {
                return receiveTask_personal.getName();
            } else {
                return null;
            }
        }
        return null;
    }
}
