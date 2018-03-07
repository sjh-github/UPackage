package com.wwdlb.hongruan.service.serviceImpl;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Attendance attendance = new Attendance();
        attendance.setEmail(email);
        attendance.setSignintime(simpleDateFormat.format(date));
        attendanceMapper.insert(attendance);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        StringBuilder nowTime = new StringBuilder(simpleDateFormat.format(date));
        nowTime.append("%");
        System.out.println(nowTime.toString());
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
        StringBuilder stringBuilder = new StringBuilder(simpleDateFormat.format(date));
        stringBuilder.append("%");
        Attendance attendance = attendanceMapper.selectByEmail(email, stringBuilder.toString());
        if (attendance == null) {
            return null;
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        attendance.setSignouttime(simpleDateFormat.format(date));
        attendanceMapper.updateByPrimaryKeySelective(attendance);
        return "签退成功";
    }

}
