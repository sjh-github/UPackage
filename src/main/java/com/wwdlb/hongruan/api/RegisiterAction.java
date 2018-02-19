package com.wwdlb.hongruan.api;

import com.wwdlb.hongruan.service.serviceImpl.RegisiterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Android注册
 */
@RestController
public class RegisiterAction {

    @Autowired
    private RegisiterServiceImpl regisiterServiceImpl;

    /**
     * 接包人员注册
     * @param email 邮箱
     * @param password 密码
     * @param name 姓名
     * @param gender 性别：男/女
     * @param birthyear 出生年份
     * @param birthmonth 出生月份
     * @param birthday 出生日期
     * @param idcard 身份证号码
     * @param phone 手机号
     * @param multipartFile 身份证照片信息
     * @param photodata 面部数据（可不提供）
     * @return true:注册成功，false:该邮箱已经被注册
     */
    @PostMapping(value = "/receiveTaskPersonal")
    public boolean regisiterReceiveTaskPersonal(@RequestParam String email, @RequestParam String password,
                                               @RequestParam String name, @RequestParam String gender,
                                               @RequestParam Integer birthyear, @RequestParam Integer birthmonth,
                                               @RequestParam Integer birthday, @RequestParam String idcard,
                                               @RequestParam String phone, @RequestParam MultipartFile multipartFile,
                                               @RequestParam(required = false) byte[] photodata) {
        return regisiterServiceImpl.regisiterReceiveTaskPersonal(email, password, name, gender, birthyear,
                    birthmonth, birthday, idcard, phone, multipartFile, photodata);
    }
}
