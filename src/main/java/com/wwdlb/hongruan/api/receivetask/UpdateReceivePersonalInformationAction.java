package com.wwdlb.hongruan.api.receivetask;

import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.UpdateProgressServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.UpdateReceiveInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android更新接包人信息
 */
@RestController
public class UpdateReceivePersonalInformationAction {
    @Autowired
    private UpdateReceiveInformationServiceImpl updateReceiveInformationServiceImpl;

    /**
     * 更新接包人信息
     * @param email 邮箱
     * @param password 密码，可无
     * @param name 姓名，可无
     * @param phone 手机号，可无
     * @param photodata 面部数据，可无
     * @return true/false
     */
    @PatchMapping(value = "/api/receiveTaskPersonal")
    public boolean updateReceiveTaskPersonalInformation(@RequestParam String email,
                                                        @RequestParam(required = false) String password,
                                                        @RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String phone,
                                                        @RequestParam(required = false) String photodata) {
        return updateReceiveInformationServiceImpl.updateReceivePersonalInformation(email, password, name, phone, photodata);
    }
}
