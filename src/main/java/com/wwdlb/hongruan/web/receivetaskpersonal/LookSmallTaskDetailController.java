package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.*;
import com.wwdlb.hongruan.pojo.CustomProgressPojo;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.GetSmallTaskByIDServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndProgressServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndTaskServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.LookSmallTaskServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class LookSmallTaskDetailController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private GetReceiveTaskPersonalServiceImpl getReceiveTaskPersonalServiceImpl;

    @Autowired
    private GetSmallTaskByIDServiceImpl getSmallTaskByIDServiceImpl;

    @Autowired
    private SmallTaskAndTaskServiceImpl smallTaskAndTaskServiceImpl;

    @Autowired
    private SmallTaskAndProgressServiceImpl smallTaskAndProgressServiceImpl;

    @Autowired
    private LookSmallTaskServiceImpl lookSmallTaskServiceImpl;

    /**
     * 查看小任务详情界面
     * @return 小任务详情界面
     */
    @GetMapping(value = "/web/smallTaskDetailPage")
    public String lookSmallTaskDetailPage(HttpServletRequest request, ModelMap modelMap, @RequestParam Integer smallTaskID) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());

        //任务详情
        modelMap.addAttribute("smallTask", getSmallTaskByIDServiceImpl.getSmallTaskByID(smallTaskID));
        Task task = smallTaskAndTaskServiceImpl.getTaskBySmallTaskID(smallTaskID);
        if (task != null) {
            modelMap.addAttribute("safeGrade", task.getSafetygrade());
            modelMap.addAttribute("priority", task.getPriority());
            modelMap.addAttribute("startTime", task.getStarttime());
        }
        SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndProgressServiceImpl.getSmallTaskAndNumberProgressBySmallTaskID(smallTaskID);
        if (smallTaskAndNumberProgress == null) {
            //自定义指标
            ArrayList<CustomProgressPojo> customProgressPojos = smallTaskAndProgressServiceImpl.getSmallTaskAndCustomProgressBySmallTaskID(smallTaskID);
            modelMap.addAttribute("customProgressPojos", customProgressPojos);
        } else {
            //数量指标
            modelMap.addAttribute("numberProgress", smallTaskAndNumberProgress);
        }
        return "workInfo";
    }

    /**
     * 查看所有小任务界面
     * @return 所有小任务界面
     */
    @GetMapping(value = "/web/smallTaskAllPage")
    public String lookSmallTaskAllPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());

        //接包人的小任务
        ArrayList<SmallTask> allSmallTask = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        modelMap.addAttribute("AllSmallTasks", allSmallTask);

        int numOfHaveFinishedSmallTask =  numOfIndexPageServiceImpl.getNumOfFinishedSmallTask(allSmallTask);
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);

        int numOfSmallTask = allSmallTask.size();
        modelMap.addAttribute("numOfSmallTask", numOfSmallTask);

        modelMap.addAttribute("numOfRunningSmallTask", numOfSmallTask - numOfHaveFinishedSmallTask);
        return "workList";
    }

    /**
     * 查看正在进行小任务界面
     * @return 正在进行小任务界面
     */
    @GetMapping(value = "/web/smallTaskRunningPage")
    public String lookSmallTaskRunningPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());

        //接包人的小任务
        ArrayList<SmallTask> allSmallTask = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        ArrayList<SmallTask> runningSmallTasks = lookSmallTaskServiceImpl.findRunningSmallTask(allSmallTask);
        modelMap.addAttribute("runningSmallTasks", runningSmallTasks);

        int numOfHaveFinishedSmallTask =  numOfIndexPageServiceImpl.getNumOfFinishedSmallTask(allSmallTask);
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);

        int numOfSmallTask = allSmallTask.size();
        modelMap.addAttribute("numOfSmallTask", numOfSmallTask);

        modelMap.addAttribute("numOfRunningSmallTask", numOfSmallTask - numOfHaveFinishedSmallTask);
        return "workList1";
    }

    /**
     * 查看已完成小任务界面
     * @return 已完成小任务界面
     */
    @GetMapping(value = "/web/smallTaskFinishedPage")
    public String lookSmallTaskRunnablePage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());

        //接包人的小任务
        ArrayList<SmallTask> allSmallTask = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        ArrayList<SmallTask> finishedSmallTasks = lookSmallTaskServiceImpl.findFinishedSmallTask(allSmallTask);
        modelMap.addAttribute("finishedSmallTasks", finishedSmallTasks);

        int numOfHaveFinishedSmallTask =  numOfIndexPageServiceImpl.getNumOfFinishedSmallTask(allSmallTask);
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);

        int numOfSmallTask = allSmallTask.size();
        modelMap.addAttribute("numOfSmallTask", numOfSmallTask);

        modelMap.addAttribute("numOfRunningSmallTask", numOfSmallTask - numOfHaveFinishedSmallTask);
        return "workList2";
    }

    /**
     * 查看个人中心里进行中小任务界面
     * @return 个人中心里进行中小任务界面
     */
    @GetMapping(value = "/web/smallTaskIndexRunningPage")
    public String lookSmallTaskIndexRunningPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
        //接包人的小任务
        ArrayList<SmallTask> allSmallTask = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        ArrayList<SmallTask> runningSmallTasks = lookSmallTaskServiceImpl.findRunningSmallTask(allSmallTask);
        modelMap.addAttribute("runningSmallTasks", runningSmallTasks);
        return "u_workList1";
    }


    /**
     * 查看个人中心已完成小任务界面
     * @return 已完成小任务界面
     */
    @GetMapping(value = "/web/smallTaskIndexFinishedPage")
    public String lookSmallTaskIndexFinishedPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
        //接包人的小任务
        ArrayList<SmallTask> allSmallTask = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        ArrayList<SmallTask> finishedSmallTasks = lookSmallTaskServiceImpl.findFinishedSmallTask(allSmallTask);
        modelMap.addAttribute("finishedSmallTasks", finishedSmallTasks);
        return "u_workList2";
    }
}
