package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
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

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @GetMapping(value = "/web/indexPage/receiveTaskPersonal")
    public String receiveTaskPersonalIndexPage(HttpServletRequest httpServletRequest, ModelMap modelMap) {
        httpSession = httpServletRequest.getSession();
        try {
            String role = (String) httpSession.getAttribute("role");
            if (role.equals(LoginServiceImpl.ReceiveTaskPersonal)) {
                String email = (String) httpSession.getAttribute("email");
                modelMap.addAttribute("email", email);
                modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
                modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
                modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
                modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
                modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
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
