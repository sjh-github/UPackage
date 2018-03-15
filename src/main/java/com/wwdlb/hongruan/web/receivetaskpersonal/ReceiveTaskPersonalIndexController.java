package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReceiveTaskPersonalIndexController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @GetMapping(value = "/web/indexPage/receiveTaskPersonal")
    public String receiveTaskPersonalIndexPage(HttpServletRequest httpServletRequest, ModelMap modelMap) {
        httpSession = httpServletRequest.getSession();
        try {
            String role = (String) httpSession.getAttribute("role");
            if (role.equals(LoginServiceImpl.ReceiveTaskPersonal)) {
                String email = (String) httpSession.getAttribute("email");
                if (email != null) {
                    modelMap.addAttribute("email", email);
                }
                int numOfReceiveTaskPersonal = numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal();
                int numOfReceiveTaskCompany = numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany();
                int numOfHaveFinishedSmallTask = numOfIndexPageServiceImpl.getNumOfFinishedSmallTask();
                modelMap.addAttribute("numOfReceiveTaskPersonal", numOfReceiveTaskPersonal);
                modelMap.addAttribute("numOfReceiveTaskCompany", numOfReceiveTaskCompany);
                modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfHaveFinishedSmallTask);
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
