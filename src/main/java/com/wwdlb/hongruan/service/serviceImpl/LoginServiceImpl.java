package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.ProvideTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ProvideTask_Personal;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登陆服务接口实例
 */
@Service
public class LoginServiceImpl/* implements LoginService*/{

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private ProvideTask_PersonalMapper provideTask_personalMapper;

    /**
     * 接包人员登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    //@Override
    public int loginReceiveTaskPersonal(String email, String password) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return -1;
        } else {
            if (!receiveTask_personal.getPassword().equals(password)) {
                return -2;
            } else {
                return 1;
            }
        }
    }

    /**
     * 发包人员登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    //@Override
    public int loginProvideTaskPersonal(String email, String password) {
        ProvideTask_Personal provideTask_personal = provideTask_personalMapper.selectByPrimaryKey(email);
        if (provideTask_personal == null) {
            return -1;
        } else {
            if (provideTask_personal.getPassword().equals(password)) {
                return 1;
            } else {
                return -2;
            }
        }
    }
}
