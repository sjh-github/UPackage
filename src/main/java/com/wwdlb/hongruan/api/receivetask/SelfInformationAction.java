package com.wwdlb.hongruan.api.receivetask;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.pojo.ReceivePersonalInformationPojo;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.AttendanceServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelfInformationAction {

    @Autowired
    private GetReceiveTaskPersonalServiceImpl receiveTaskPersonalServiceImpl;

    @Autowired
    private AttendanceServiceImpl attendanceServiceImpl;

    /**
     * 获取接包人基本信息
     * @param email 邮箱
     * @return 接包人基本信息
     */
    @GetMapping(value = "/api/receiveTaskPersonal")
    public ReceivePersonalInformationPojo getReceiveTaskPersonInformation(@RequestParam String email) {
        ReceivePersonalInformationPojo receivePersonalInformationPojo = new ReceivePersonalInformationPojo();
        ReceiveTask_Personal receiveTask_personal = receiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal == null) {
            return null;
        }
        receivePersonalInformationPojo.setName(receiveTask_personal.getName());
        receivePersonalInformationPojo.setPhone(receiveTask_personal.getPhone());
        if (receiveTask_personal.getGender() == 0) {
            receivePersonalInformationPojo.setGender("男");
        } else {
            receivePersonalInformationPojo.setGender("女");
        }
        receivePersonalInformationPojo.setHaveSignInDay(attendanceServiceImpl.getHaveSignInDay(email));
        receivePersonalInformationPojo.setTodaySignInTime(attendanceServiceImpl.getTodaySignInTime(email));
        return receivePersonalInformationPojo;
    }
}
