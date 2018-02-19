package com.wwdlb.hongruan.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 注册服务接口
 */
public interface RegisiterService {

    //接包人员注册
    boolean regisiterReceiveTaskPersonal(String email, String password, String name, String gender,
                                       Integer birthyear, Integer birthmonth, Integer birthday, String idcard,
                                       String phone, MultipartFile multipartFile, byte[] photodata);
}
