package com.wwdlb.hongruan.web.providetaskpersonal;

import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SmallTaskManageController {

    private HttpSession httpSession;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    /**
     * 查看所有小任务界面
     * @param request request
     * @param modelMap modelMap
     * @return 所有小任务界面
     */
    @GetMapping(value = "/web/provideTaskPersonal/SmallTask/All")
    public String allSmallTaskPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        int numOfSmallTask = numOfIndexPageServiceImpl.getNumOfSmallTask();
        modelMap.addAttribute("numOfSmallTask", numOfSmallTask);
        int numOfHaveFinishedSmallTask =  numOfIndexPageServiceImpl.getNumOfFinishedSmallTask();
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);
        modelMap.addAttribute("numOfRunningSmallTask", numOfSmallTask - numOfHaveFinishedSmallTask);
        return "r_workList";
    }

    /**
     * 查看正在进行小任务界面
     * @param request request
     * @param modelMap modelMap
     * @return 正在进行小任务界面
     */
    @GetMapping(value = "/web/provideTaskPersonal/SmallTask/Running")
    public String runningSmallTaskPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        int numOfSmallTask = numOfIndexPageServiceImpl.getNumOfSmallTask();
        modelMap.addAttribute("numOfSmallTask", numOfSmallTask);
        int numOfHaveFinishedSmallTask =  numOfIndexPageServiceImpl.getNumOfFinishedSmallTask();
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);
        modelMap.addAttribute("numOfRunningSmallTask", numOfSmallTask - numOfHaveFinishedSmallTask);
        return "r_workList1";
    }

    /**
     * 查看已完成小任务界面
     * @param request request
     * @param modelMap modelMap
     * @return 已完成小任务界面
     */
    @GetMapping(value = "/web/provideTaskPersonal/SmallTask/Finished")
    public String finishedSmallTaskPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        int numOfHaveFinishedSmallTask =  numOfIndexPageServiceImpl.getNumOfFinishedSmallTask();
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);
        int numOfSmallTask = numOfIndexPageServiceImpl.getNumOfSmallTask();
        modelMap.addAttribute("numOfSmallTask", numOfSmallTask);
        modelMap.addAttribute("numOfRunningSmallTask", numOfSmallTask - numOfHaveFinishedSmallTask);
        return "r_workList2";
    }
}
