package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetSignTimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonalCenterSmallTaskController {

    @Autowired
    private GetSignTimeServiceImpl getSignTimeServiceImpl;

    private HttpSession httpSession;
    /**
     * 个人任务里面的小任务界面
     * @return 个人任务里面的小任务界面
     */
    @GetMapping(value = "/web/personalCenter/smallTaskPage")
    public String personalCenterSmallTask(ModelMap modelMap, HttpServletRequest request) {
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        modelMap.addAttribute("signInTime", getSignTimeServiceImpl.getSignInTime(email));
        modelMap.addAttribute("signOutTime", getSignTimeServiceImpl.getSignOutTime(email));
        return "u_workList1";
    }
}
