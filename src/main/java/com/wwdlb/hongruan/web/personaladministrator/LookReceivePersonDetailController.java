package com.wwdlb.hongruan.web.personaladministrator;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LookReceivePersonDetailController {

    private HttpSession httpSession;

    @Autowired
    private GetReceiveTaskPersonalServiceImpl getReceiveTaskPersonalServiceImpl;

    @GetMapping(value = "/web/receiveTaskPersonal/detail")
    public String lookReceiveTaskPersonDetailPage(@RequestParam String email, HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String thisEmail = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("email", thisEmail);
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            modelMap.addAttribute("receiveTask_personal", receiveTask_personal);
            String topIDFile = receiveTask_personal.getIdfile();
            String downIDFile;
            if (topIDFile.contains("top")) {
                downIDFile = topIDFile.replace("top", "down");
            } else {
                downIDFile = topIDFile;
            }
            modelMap.addAttribute("topIDFile", topIDFile);
            modelMap.addAttribute("downIDFile", downIDFile);
            if (httpSession.getAttribute("role").equals(LoginServiceImpl.PersonnelAdministrator)) {
                modelMap.addAttribute("isPersonalAdministrator", "true");
            } else if (httpSession.getAttribute("role").equals(LoginServiceImpl.ProvideTaskPersonal)) {
                modelMap.addAttribute("isProvideTaskPerson", "true");
            }
            return "desHome";
        } else {
            return "redirect:/web/receiveTaskPersonal/no";
        }
    }
}
