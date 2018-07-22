package com.wwdlb.hongruan.web.providetaskpersonal;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.wwdlb.hongruan.Info.Info;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;
import com.wwdlb.hongruan.model.Task;
import com.wwdlb.hongruan.pojo.CustomProgressPojo;
import com.wwdlb.hongruan.service.serviceImpl.*;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ShowSmallTaskByProvidePersonEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import groovy.util.IFileNameFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SmallTaskManageController {

    @Value("${my.perpage}")
    private int perpage;

    private HttpSession httpSession;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private ShowSmallTaskByProvidePersonEmailServiceImpl showSmallTaskByProvidePersonEmailServiceImpl;

    @Autowired
    private GetReceiveTaskPersonalServiceImpl getReceiveTaskPersonalServiceImpl;

    @Autowired
    private GetSmallTaskByIDServiceImpl getSmallTaskByIDServiceImpl;

    @Autowired
    private SmallTaskAndTaskServiceImpl smallTaskAndTaskServiceImpl;

    @Autowired
    private SmallTaskAndProgressServiceImpl smallTaskAndProgressServiceImpl;

    @Autowired
    private PermissionCheckServiceImpl permissionCheckServiceImpl;

    /**
     * 查看小任务详情界面
     * @return 小任务详情界面
     */
    @GetMapping(value = "/web/provideTaskPerson/smallTaskDetailPage")
    public String lookSmallTaskDetailPage(HttpServletRequest request, ModelMap modelMap, @RequestParam Integer smallTaskID) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        //权限检测
        if (!permissionCheckServiceImpl.havePermission(email, smallTaskID, Info.provideSmallTaskPermission)) {
            return "redirect:/web/provideTaskPersonal/SmallTask/All?havePermission=false";
        }
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            int birthyear = receiveTask_personal.getBirthyear();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
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

        //日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        simpleDateFormat.format(date);
        modelMap.addAttribute("nowTime", simpleDateFormat.format(date));

        //进度
        Integer progress = smallTaskAndProgressServiceImpl.getProgressBySmallTaskID(smallTaskID);
        modelMap.addAttribute("progress",progress);
        return "r_workInfo";
    }


    /**
     * 查看所有小任务界面
     * @param request request
     * @param modelMap modelMap
     * @return 所有小任务界面
     */
    @GetMapping(value = "/web/provideTaskPersonal/SmallTask/All")
    public String allSmallTaskPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false)Integer page, @RequestParam(required = false)String havePermission) {
        if (page == null || page < 1) {
            page = 1;
        }
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        //所有该发包人发布的小任务
        ArrayList<SmallTask> allSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getAllSmallTaskByProvidePersonEmail(email);
        PageInfo<SmallTask> allSmallTaskPageList = showSmallTaskByProvidePersonEmailServiceImpl.getAllSmallTaskByProvidePersonEmail(email, page, perpage);
        modelMap.addAttribute("allSmallTasks", allSmallTaskPageList);
        modelMap.addAttribute("nowPage", page);
        //该发包人所有发布的小任务数量
        Integer allSmallTaskNum = allSmallTaskList.size();
        modelMap.addAttribute("allSmallTaskNum",allSmallTaskNum);
        //该发包人已完成的小任务数量
        ArrayList<SmallTask> finishedSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getFinishedSmallTaskByProvidePersonEmail(email);
        Integer  finishedSmallTaskNum;
        if (finishedSmallTaskList == null) {
            finishedSmallTaskNum = 0;
        } else {
            finishedSmallTaskNum = finishedSmallTaskList.size();
        }
        modelMap.addAttribute("finishedSmallTaskNum", finishedSmallTaskNum);
        modelMap.addAttribute("runningSmallTaskNum", allSmallTaskNum - finishedSmallTaskNum);

        if (havePermission != null) {
            modelMap.addAttribute("havePermission", havePermission);
        }
        return "r_workList";
    }

    /**
     * 查看正在进行小任务界面
     * @param request request
     * @param modelMap modelMap
     * @return 正在进行小任务界面
     */
    @GetMapping(value = "/web/provideTaskPersonal/SmallTask/Running")
    public String runningSmallTaskPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false)Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        //正在进行该发包人发布的小任务
        ArrayList<SmallTask> runningSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getRunningSmallTaskByProvidePersonEmail(email);
        PageInfo<SmallTask> runningSmallTaskListPage = showSmallTaskByProvidePersonEmailServiceImpl.getRunningSmallTaskByProvidePersonEmail(email, page, perpage);
        modelMap.addAttribute("runningSmallTaskList", runningSmallTaskListPage);
        modelMap.addAttribute("nowPage", page);
        //该发包人正在进行小任务数量
        Integer runningSmallTaskNum = runningSmallTaskList.size();
        modelMap.addAttribute("runningSmallTaskNum", runningSmallTaskNum);
        //该发包人所有已发布小任务数量
        ArrayList<SmallTask> allSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getAllSmallTaskByProvidePersonEmail(email);
        Integer allSmallTaskNum;
        if (allSmallTaskList == null) {
            allSmallTaskNum = 0;
        } else {
            allSmallTaskNum = allSmallTaskList.size();
        }
        modelMap.addAttribute("allSmallTaskNum", allSmallTaskNum);
        //该发包人已完成小任务数量
        modelMap.addAttribute("finishedSmallTaskNum", allSmallTaskNum - runningSmallTaskNum);
        return "r_workList1";
    }

    /**
     * 查看已完成小任务界面
     * @param request request
     * @param modelMap modelMap
     * @return 已完成小任务界面
     */
    @GetMapping(value = "/web/provideTaskPersonal/SmallTask/Finished")
    public String finishedSmallTaskPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false)Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        //已完成该发包人发布的小任务
        ArrayList<SmallTask> finishedSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getFinishedSmallTaskByProvidePersonEmail(email);
        PageInfo<SmallTask> finishedSmallTaskListPage = showSmallTaskByProvidePersonEmailServiceImpl.getFinishedSmallTaskByProvidePersonEmail(email, page, perpage);
        modelMap.addAttribute("finishedSmallTaskList", finishedSmallTaskListPage);
        modelMap.addAttribute("nowPage", page);
        //该发包人已完成小任务数量
        Integer finishedSmallTaskNum = finishedSmallTaskList.size();
        modelMap.addAttribute("finishedSmallTaskNum", finishedSmallTaskNum);
        //该发包人所有已发布小任务数量
        ArrayList<SmallTask> allSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getAllSmallTaskByProvidePersonEmail(email);
        Integer allSmallTaskNum;
        if (allSmallTaskList == null) {
            allSmallTaskNum = 0;
        } else {
            allSmallTaskNum = allSmallTaskList.size();
        }
        modelMap.addAttribute("allSmallTaskNum", allSmallTaskNum);
        //该发包人正在进行小任务数量
        modelMap.addAttribute("runningSmallTaskNum", allSmallTaskNum - finishedSmallTaskNum);
        return "r_workList2";
    }
}
