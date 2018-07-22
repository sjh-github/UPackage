package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.AttendanceMapper;
import com.wwdlb.hongruan.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考勤服务实例
 */
@Service
@Transactional
public class AttendanceServiceImpl {

    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 签到
     * @param email 接包人员邮箱
     * @return 签到结果及累计天数
     */
    public String signIn(String email) {
        /*检查本日是否已签到*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        StringBuilder nowTime = new StringBuilder(simpleDateFormat.format(date));
        nowTime.append("%");
        Attendance attendance = attendanceMapper.selectByEmail(email, nowTime.toString());
        if (attendance != null) {
            return "签到失败，您本日已签到";
        }
        /*进行签到*/
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Attendance todayAttendance = new Attendance();
        todayAttendance.setEmail(email);
        todayAttendance.setSignintime(simpleDateFormat.format(date));
        attendanceMapper.insert(todayAttendance);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        nowTime = new StringBuilder(simpleDateFormat.format(date));
        nowTime.append("%");
        int num = attendanceMapper.getSignDayNumOfMonth(email, nowTime.toString());
        return "签到成功，您本月已成功签到 " + num + " 天";
    }

    /**
     * 签退
     * @param email 接包人员邮箱
     * @return 签退结果/NULL
     */
    public String signOut(String email) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Attendance attendance = attendanceMapper.selectByEmail(email, "%" + simpleDateFormat.format(date) + "%");
        if (attendance == null) {
            System.out.println(email);
            System.out.println(simpleDateFormat.format(date) + "%");
            return "签退失败，您本日尚未签到";
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        attendance.setSignouttime(simpleDateFormat.format(date));
        attendanceMapper.updateByPrimaryKeySelective(attendance);
        return "签退成功";
    }

    /**
     * 获取本月已签到天数
     * @param email 邮箱
     * @return 本月已签到天数
     */
    public int getHaveSignInDay(String email) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        return attendanceMapper.getSignDayNumOfMonth(email, simpleDateFormat.format(date) + "%");
    }

    /**
     * 获取本日签到时间
     * @param email 邮箱
     * @return NULL/本日签到时间
     */
    public String getTodaySignInTime(String email) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Attendance attendance = attendanceMapper.selectByEmail(email, simpleDateFormat.format(date) + "%");
        if (attendance == null) {
            return null;
        }
        return attendance.getSignintime();
    }
}
