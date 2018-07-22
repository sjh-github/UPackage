package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetSignTimeServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.PersonalIdentityConfimServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PersonalIdentityConfimController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private GetReceiveTaskPersonalServiceImpl getReceiveTaskPersonalServiceImpl;

    @Autowired
    private GetSignTimeServiceImpl getSignTimeServiceImpl;

    @Autowired
    private PersonalIdentityConfimServiceImpl personalIdentityConfimServiceImpl;

    /**
     * 个人身份认证界面
     * @return 个人身份认证界面
     */
    @GetMapping(value = "/web/personalIdentityConfimPage")
    public String identityConfimPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false)String result) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
            modelMap.addAttribute("idCard", receiveTask_personal.getIdcard());
            String topIDFile = receiveTask_personal.getIdfile();
            modelMap.addAttribute("topIDFile", topIDFile);
            if (topIDFile != null && topIDFile.contains("top"));
            modelMap.addAttribute("downIDFile", topIDFile.replace("top", "down"));
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
        modelMap.addAttribute("signInTime", getSignTimeServiceImpl.getSignInTime(email));
        modelMap.addAttribute("signOutTime", getSignTimeServiceImpl.getSignOutTime(email));
        if (result != null) {
            modelMap.addAttribute("result", result);
        }
        return "u_renzheng1";
    }

    @PostMapping(value = "/web/personalIdentityConfim")
    public String personalIdentityConfim(HttpServletRequest request, @RequestParam String name, @RequestParam String idCard,
                                         @RequestParam MultipartFile topIDFile, @RequestParam MultipartFile downIDFile) {
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        if (personalIdentityConfimServiceImpl.personalIdentityConfim(email, name, idCard, topIDFile, downIDFile)) {
            return "redirect:/web/personalIdentityConfimPage?result=true";
        }
        return "redirect:/web/personalIdentityConfimPage?result=false";
    }
}
