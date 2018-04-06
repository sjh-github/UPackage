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
public class ProvideSmallTaskController {

    private HttpSession httpSession;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;


    @GetMapping(value = "/web/smallTaskPage")
    public String newSmallTaskPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        return "demand";
    }
}
