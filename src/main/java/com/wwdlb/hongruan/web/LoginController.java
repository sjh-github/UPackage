package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private HttpSession httpSession;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    /**
     * 登陆界面映射
     * @return 登陆界面视图
     */
    @GetMapping(value = "/web/loginPage")
    public String loginPage() {
        return "login";
    }


    /**
     * 登陆控制器
     * @param email 邮箱
     * @param password 密码
     * @param httpServletRequest 获取session
     * @return 登陆界面/首界面
     */
    @PostMapping(value = "/web/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest httpServletRequest) {
        String result = loginServiceImpl.login(email, password);
        switch (result) {
            case LoginServiceImpl.WrongPassword :
                return "redirect:/web/loginPage";
            case LoginServiceImpl.HaveNoAccount :
                return "redirect:/web/loginPage";
            case LoginServiceImpl.ReceiveTaskPersonal :
                httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("role", LoginServiceImpl.ReceiveTaskPersonal);
                httpSession.setAttribute("email", email);
                return "redirect:/web/indexPage/receiveTaskPersonal";
            case LoginServiceImpl.ProvideTaskPersonal :
                httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("role", LoginServiceImpl.ProvideTaskPersonal);
                httpSession.setAttribute("email", email);
                return "redirect:/web/indexPage/provideTaskPersonal";
            case LoginServiceImpl.ReceiveTaskCompany :
                httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("role", LoginServiceImpl.ReceiveTaskCompany);
                httpSession.setAttribute("email", email);
                return "redirect:/web/indexPage/receiveTaskCompany";
            case LoginServiceImpl.PersonnelAdministrator :
                httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("role", LoginServiceImpl.PersonnelAdministrator);
                httpSession.setAttribute("email", email);
                return "redirect:/web/indexPage/personalAdministrator";
            case LoginServiceImpl.SuperAdministrator :
                httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("role", LoginServiceImpl.SuperAdministrator);
                httpSession.setAttribute("email", email);
                return "redirect:/web/indexPage/superAdministrator";
            default :
                return "redirect:/web/loginPage";
        }
    }
}
