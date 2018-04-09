package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改密码服务实例
 */
@Service
public class ChangePasswordServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 修改密码
     * @param email 被修改人邮箱
     * @param oldPsw 旧密码
     * @param newPsw 新密码
     * @return 修改结果
     */
    public boolean updatePassword(String email, String oldPsw, String newPsw) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return false;
        } else if (!receiveTask_personal.getPassword().equals(oldPsw)) {
            return false;
        } else {
            return true;
        }
    }
}
