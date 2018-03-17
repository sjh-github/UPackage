package com.wwdlb.hongruan.api.receivetask;

import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.AttendanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android接包人员考勤
 */
@RestController
public class SignAction {

    @Autowired
    private AttendanceServiceImpl attendanceServiceImpl;

    /**
     * 签到
     * @param email 接包人员邮箱
     * @return 签到结果及累计天数
     */
    @PostMapping(value = "/api/attendance")
    public String singIn(@RequestParam String email) {
        return attendanceServiceImpl.signIn(email);
    }

    /**
     * 签退
     * @param email 接包人员邮箱
     * @return 签退结果/NULL
     */
    @PatchMapping(value = "/api/attendance")
    public String singOut(@RequestParam String email) {
        return attendanceServiceImpl.signOut(email);
    }
}
