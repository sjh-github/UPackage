package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @GetMapping(value = "/web/loginPage")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/web/findPswPage")
    public String findPswPage() {
        return "login_chage_psw";
    }

}
