package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangePersonalInformationController {


    /**
     * 修改个人信息界面映射
     * @return 修改个人信息界面
     */
    @GetMapping(value = "/web/changePersonalInformationPage")
    public String changePersonalInformationPage() {
        return "u_ziliao";
    }

    /**
     * 修改密码界面映射
     * @return 修改密码界面
     */
    @GetMapping(value = "/web/changePasswordPage")
    public String changePasswordPage() {
        return "/u_change_psw";
    }

    /**
     * 修改手脚界面映射
     * @return 修改手机号界面
     */
    @GetMapping(value = "/web/changePhonePage")
    public String changePhonePage() {
        return "/u_change_phone";
    }

}
