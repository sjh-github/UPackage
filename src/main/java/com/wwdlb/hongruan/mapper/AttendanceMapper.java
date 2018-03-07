package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.Attendance;
import org.apache.ibatis.annotations.Param;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Integer attendanceid);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Integer attendanceid);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    int getSignDayNumOfMonth(@Param("email") String email, @Param("nowTime") String nowTime);

    Attendance selectByEmail(@Param("email") String email, @Param("nowTime") String nowTime);
}