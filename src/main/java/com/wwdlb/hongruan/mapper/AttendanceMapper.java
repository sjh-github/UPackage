package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.Attendance;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Integer attendanceid);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Integer attendanceid);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    int getSignDayNumOfMonth(@Param("email") String email, @Param("nowTime") String nowTime);

    Attendance selectByEmail(@Param("email") String email, @Param("nowTime") String nowTime);

    ArrayList<String> selectHaveRunningTaskEmailTodayHaveSigned(@Param("haveRunningSmallTaskPersonEmailList") ArrayList<String> haveRunningSmallTaskPersonEmailList,
                                                                @Param(("nowTime")) String nowTme);

    /*Attendance selectByEmailAnd(@Param("email")String email, @Param("nowTime") String nowTime);*/
}