package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 更新接包人信息服务实例
 */
@Transactional
@Service
public class UpdateReceiveInformationServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 更新接包人信息
     * @param email 邮箱
     * @param password 密码，可无
     * @param name 姓名，可无
     * @param phone 手机号，可无
     * @param photodata 面部数据，可无
     * @return true/false
     */
    public boolean updateReceivePersonalInformation(String email, String password, String name, String phone, String photodata) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return false;
        }
        if (password != null) {
            receiveTask_personal.setPassword(password);
        }
        if (name != null) {
            receiveTask_personal.setName(name);
        }
        if (phone != null) {
            receiveTask_personal.setPhone(phone);
        }
        if (photodata != null) {
            receiveTask_personal.setPhotodata(photodata);
        }
        receiveTask_personalMapper.updateByPrimaryKeySelective(receiveTask_personal);
        return true;
    }
}
