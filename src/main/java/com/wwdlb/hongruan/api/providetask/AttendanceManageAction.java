package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.pojo.ReceivePersonAndSignNumPojo;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.AttendanceManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android考勤管理
 */
@RestController
public class AttendanceManageAction {

    @Autowired
    private AttendanceManageServiceImpl attendanceManageServiceImpl;

    /**
     * 获取所有有小任务本日已签到人员及其本月已签到天数
     * @return NULL/所有本日已签到人员及其签到天数
     */
    @GetMapping(value = "/api/attendance/yes")
    public ArrayList<ReceivePersonAndSignNumPojo> getAllTodayHaveSignInPerson() {
        return attendanceManageServiceImpl.getAllTodayHaveSignInPerson();
    }

    /**
     * 获取所有有小任务本日未签到人员及其本月已签到天数
     * @return NULL/所有本日未签到人员及其签到天数
     */
    @GetMapping(value = "/api/attendance/no")
    public ArrayList<ReceivePersonAndSignNumPojo> getAllTodayNoSignInPerson() {
        return attendanceManageServiceImpl.getAllTodayNoSignInPerson();
    }
}
