package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.LookAllSmallTaskByEmailServiceImpl;
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
    private LookAllSmallTaskByEmailServiceImpl lookAllSmallTaskByEmailServiceImpl;

    @GetMapping(value = "/web/indexPage/receiveTaskPersonal")
    public String receiveTaskPersonalIndexPage(HttpServletRequest httpServletRequest, ModelMap modelMap) {
        httpSession = httpServletRequest.getSession();
        try {
            String role = (String) httpSession.getAttribute("role");
            if (role.equals(LoginServiceImpl.ReceiveTaskPersonal)) {
                String email = (String) httpSession.getAttribute("email");
                if (email != null) {
                    modelMap.addAttribute("email", email);
                    /*ArrayList<SmallTask> smallTaskArrayList = lookAllSmallTaskByEmailServiceImpl.lookAllSmallTaskByEmail(email);
                    modelMap.addAttribute("smallTaskArrayList", smallTaskArrayList);*/
                }
                int numOfReceiveTaskPersonal = numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal();
                int numOfReceiveTaskCompany = numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany();
                int numOfHaveFinishedSmallTask = numOfIndexPageServiceImpl.getNumOfFinishedSmallTask();
                int numOfSmallTask = numOfIndexPageServiceImpl.getNumOfSmallTask();
                modelMap.addAttribute("numOfReceiveTaskPersonal", numOfReceiveTaskPersonal);
                modelMap.addAttribute("numOfReceiveTaskCompany", numOfReceiveTaskCompany);
                modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);
                modelMap.addAttribute("numOfSmallTask", numOfSmallTask);
                return "receiveTaskPersonalIndex";
            } else {
                return "redirect:/web/loginPage";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/web/loginPage";
        }
    }
}
