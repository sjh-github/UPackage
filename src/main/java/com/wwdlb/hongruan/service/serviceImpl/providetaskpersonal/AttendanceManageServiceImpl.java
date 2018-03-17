package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.AttendanceMapper;
import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.pojo.ReceivePersonAndSignNumPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 考勤管理服务实例
 */
@Service
public class AttendanceManageServiceImpl {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 获取所有有小任务本日已签到人员及其本月已签到天数
     * @return NULL/所有有小任务的本日已签到人员及其签到天数
     */
    public ArrayList<ReceivePersonAndSignNumPojo> getAllTodayHaveSignInPerson() {
        ArrayList<String> haveRunningSmallTaskPersonEmailList = getAllHaveRuningSmallTaskPersonEmail();
        if (haveRunningSmallTaskPersonEmailList == null) {
            return null;
        }

        ArrayList<String> todayHaveSignedEmailList = getHaveSignInPersonEmail(haveRunningSmallTaskPersonEmailList);
        //本日无已签到人员
        if (todayHaveSignedEmailList == null) {
            return null;
        }

        return getReceivePersonAndSignNumPojo(todayHaveSignedEmailList);
    }

    /**
     * 获取所有有小任务本日未签到人员及其本月已签到天数
     * @return NULL/所有本日未签到人员及其签到天数
     */
    public ArrayList<ReceivePersonAndSignNumPojo> getAllTodayNoSignInPerson() {
        ArrayList<String> haveRunningSmallTaskPersonEmailList = getAllHaveRuningSmallTaskPersonEmail();
        if (haveRunningSmallTaskPersonEmailList == null) {
            return null;
        }
        ArrayList<String> noSignInPersonEmail = getNoSignInPersonEmail(haveRunningSmallTaskPersonEmailList);
        if (noSignInPersonEmail == null) {
            return null;
        }

        return getReceivePersonAndSignNumPojo(noSignInPersonEmail);
    }

    /**
     * 获取所有目前有正在进行的小任务的接包人员邮箱列表
     * @return NULL/目前有正在进行的小任务的接包人员邮箱列表
     */
    private ArrayList<String> getAllHaveRuningSmallTaskPersonEmail() {
        List<ReceiveTask_Personal> receiveTaskPersonalList = receiveTask_personalMapper.selectAll();
        //暂无接包人员列表
        if (receiveTaskPersonalList == null) {
            return null;
        }
        //获取所有有正在进行小任务的人员列表
        ArrayList<String> haveRunningSmallTaskPersonEmailList = new ArrayList<>();
        for (ReceiveTask_Personal receiveTask_personal : receiveTaskPersonalList) {
            //该接包人员对应的小任务ID列表
            ArrayList<Integer> smallTaskIDArrayList = personAndSmallTaskMapper.selectSmallTaskIDByEmail(receiveTask_personal.getEmail());
            if (smallTaskIDArrayList != null){
                for (int smallTaskID : smallTaskIDArrayList) {
                    SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
                    if (smallTask != null) {
                        if (smallTask.getHavefinished().equals("F")) {
                            haveRunningSmallTaskPersonEmailList.add(receiveTask_personal.getEmail());
                            break;
                        }
                    }
                }
            }
        }
        return haveRunningSmallTaskPersonEmailList;
    }

    /**
     * 获取本日已签到邮箱列表
     * @param personEmailList 查找人员邮箱列表
     * @return NULL/已签到列表
     */
    private ArrayList<String> getHaveSignInPersonEmail(ArrayList<String> personEmailList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return attendanceMapper.selectHaveRunningTaskEmailTodayHaveSigned(personEmailList, simpleDateFormat.format(date) + "%");
    }

    /**
     * 获取本日未签到邮箱列表
     * @param personEmailList 查找人员邮箱列表
     * @return NULL/未签到列表
     */
    private ArrayList<String> getNoSignInPersonEmail(ArrayList<String> personEmailList) {
        ArrayList<String> haveSignInPersonEmail = getHaveSignInPersonEmail(personEmailList);
        personEmailList.removeAll(haveSignInPersonEmail);
        return personEmailList;
    }

    /**
     * 查找指定接包人员本月已登陆天数
     * @param emailList 邮箱列表
     * @return NULL/接包人员及其本月已登陆天数
     */
    private ArrayList<ReceivePersonAndSignNumPojo> getReceivePersonAndSignNumPojo(ArrayList<String> emailList) {
        if (emailList == null) {
            return null;
        }
        ArrayList<ReceivePersonAndSignNumPojo> receivePersonAndSignNumPojoArrayList = new ArrayList<>();
        for (String email : emailList) {
            ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
            if (receiveTask_personal != null) {
                ReceivePersonAndSignNumPojo receivePersonAndSignNumPojo = new ReceivePersonAndSignNumPojo();
                receivePersonAndSignNumPojo.setEmail(email);
                receivePersonAndSignNumPojo.setName(receiveTask_personal.getName());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                Date date = new Date();
                int haveSignDay = attendanceMapper.getSignDayNumOfMonth(email, simpleDateFormat.format(date) + "%");
                receivePersonAndSignNumPojo.setHaveSignInDay(haveSignDay);
                receivePersonAndSignNumPojoArrayList.add(receivePersonAndSignNumPojo);
            }
        }
        return receivePersonAndSignNumPojoArrayList;
    }
}
