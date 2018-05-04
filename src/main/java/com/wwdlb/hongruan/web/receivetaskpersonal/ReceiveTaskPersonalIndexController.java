package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetSignTimeServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.LookSmallTaskServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ReceiveTaskPersonalIndexController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private LookSmallTaskServiceImpl lookSmallTaskServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private GetSignTimeServiceImpl getSignTimeServiceImpl;

    @GetMapping(value = "/web/indexPage/receiveTaskPersonal")
    public String receiveTaskPersonalIndexPage(HttpServletRequest httpServletRequest, ModelMap modelMap) {
        httpSession = httpServletRequest.getSession();
        try {
                String email = (String) httpSession.getAttribute("email");
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
        } catch (Exception e) {
            return "redirect:http://115.159.71.92/hongruan/web/loginPage";
        }
    }
}
