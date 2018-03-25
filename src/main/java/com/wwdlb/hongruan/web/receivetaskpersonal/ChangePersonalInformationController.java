package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChangePersonalInformationController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private GetReceiveTaskPersonalServiceImpl getReceiveTaskPersonalServiceImpl;

    /**
     * 修改个人信息界面映射
     * @return 修改个人信息界面
     */
    @GetMapping(value = "/web/changePersonalInformationPage")
    public String changePersonalInformationPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
            modelMap.addAttribute("phone", receiveTask_personal.getPhone());
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
        return "u_ziliao";
    }

    /**
     * 修改密码界面映射
     * @return 修改密码界面
     */
    @GetMapping(value = "/web/changePasswordPage")
    public String changePasswordPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            int birthyear = receiveTask_personal.getBirthyear();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
        return "/u_change_psw";
    }

    /**
     * 修改手机号界面映射
     * @return 修改手机号界面
     */
    @GetMapping(value = "/web/changePhonePage")
    public String changePhonePage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            Date date = new Date();
            int birthyear = receiveTask_personal.getBirthyear();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
        return "u_change_phone";
    }

}
