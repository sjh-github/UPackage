package com.wwdlb.hongruan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindPasswordController {
    /**
     * 找回密码界面映射
     * @return 找回密码界面视图
     */
    @GetMapping(value = "/web/findPswPage")
    public String findPswPage() {
        return "login_chage_psw";
    }
}
