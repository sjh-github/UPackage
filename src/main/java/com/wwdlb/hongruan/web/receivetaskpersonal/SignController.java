package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.AttendanceServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetSignTimeServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.LookSmallTaskServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 签到签退
 */
@Controller
public class SignController {

    @Autowired
    private AttendanceServiceImpl attendanceServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private LookSmallTaskServiceImpl lookSmallTaskServiceImpl;

    @Autowired
    private GetSignTimeServiceImpl getSignTimeServiceImpl;

    private HttpSession httpSession;

    @GetMapping(value = "/web/signIn")
    public String signIn(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        attendanceServiceImpl.signIn(email);

        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());

        //接包人的小任务
        ArrayList<SmallTask> allSmallTasks = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        modelMap.addAttribute("AllSmallTasks", lookSmallTaskServiceImpl.findRunningSmallTask(allSmallTasks));

        modelMap.addAttribute("signInTime", getSignTimeServiceImpl.getSignInTime(email));
        modelMap.addAttribute("signOutTime", getSignTimeServiceImpl.getSignOutTime(email));
        return "receiveTaskPersonalIndex";
    }

    @GetMapping(value = "/web/signOut")
    public String signOut(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        attendanceServiceImpl.signOut(email);

        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());

        //接包人的小任务
        ArrayList<SmallTask> allSmallTasks = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        modelMap.addAttribute("AllSmallTasks", lookSmallTaskServiceImpl.findRunningSmallTask(allSmallTasks));

        modelMap.addAttribute("signInTime", getSignTimeServiceImpl.getSignInTime(email));
        modelMap.addAttribute("signOutTime", getSignTimeServiceImpl.getSignOutTime(email));
        return "receiveTaskPersonalIndex";
    }
}
