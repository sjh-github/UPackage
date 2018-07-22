package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.AttendanceMapper;
import com.wwdlb.hongruan.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获的签到签退时间服务实例
 */
@Service
public class GetSignTimeServiceImpl {
   @Autowired
    private AttendanceMapper attendanceMapper;

   public String getSignInTime(String email) {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();
       Attendance attendance = attendanceMapper.selectByEmail(email, "%" + simpleDateFormat.format(date) + "%");
       if (attendance != null) {
           String signInTime = attendance.getSignintime();
           String[] strs = null;
           if (signInTime != null) {
                strs = signInTime.split(" ");
               return strs[1];
           }
       }
       return null;
   }

   public String getSignOutTime(String email) {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();
       Attendance attendance = attendanceMapper.selectByEmail(email, "%" + simpleDateFormat.format(date) + "%");
       if (attendance != null) {
           String signInTime = attendance.getSignouttime();
           String[] strs = null;
           if (signInTime != null) {
               strs = signInTime.split(" ");
               return strs[1];
           }
       }
       return null;
   }
}
