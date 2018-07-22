package com.wwdlb.hongruan.api;

import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Android登陆
 */
@RestController
public class LoginAction {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    /**
     * 接包人员登陆
     * @param email 邮箱
     * @param password 密码（邮箱末尾需多加个 .）
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    @PostMapping(value = "/api/receiveTaskPersonal/{email}")
    public int loginReceiveTaskPersonal(@PathVariable String email, @RequestParam String password) {
        return loginServiceImpl.loginReceiveTaskPersonal(email, password);
    }

    /**
     * 发包人员登陆
     * @param email 邮箱（邮箱末尾需多加个 .）
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    @PostMapping(value = "/api/provideTaskPersonal/{email}")
    public int loginProvideTaskPersonal(@PathVariable String email, @RequestParam String password) {
        return loginServiceImpl.loginProvideTaskPersonal(email, password);
    }
}
