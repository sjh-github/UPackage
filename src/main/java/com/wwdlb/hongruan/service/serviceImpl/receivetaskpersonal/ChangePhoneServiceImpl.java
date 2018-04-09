package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改手机号服务实例
 */
@Service
public class ChangePhoneServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 修改密码
     * @param email 被修改人邮箱
     * @param phone 手机号
     * @return 修改结果
     */
    public boolean updatePassword(String email, String phone) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return false;
        } else {
            receiveTask_personal.setPhone(phone);
            receiveTask_personalMapper.updateByPrimaryKeySelective(receiveTask_personal);
            return true;
        }
    }
}
