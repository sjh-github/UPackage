package com.wwdlb.hongruan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    private HttpSession httpSession;
    /**
     * 登出系统
     * @param httpServletRequest  request
     * @return 重定向登陆界面
     */
    @GetMapping(value = "/web/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("role");
        httpSession.removeAttribute("email");
        httpSession.invalidate();
        return "redirect:/web/loginPage";
    }
}
