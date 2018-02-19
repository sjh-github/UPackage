package com.wwdlb.hongruan.service;

/**
 * 登陆服务接口
 */
public interface LoginService {
    //接包人员登陆
    int loginReceiveTaskPersonal(String email, String password);
    //发包人员登陆
    int loginProvideTaskPersonal(String email, String password);
}
