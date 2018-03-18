package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalIdentityConfimController {


    /**
     * 个人身份认证界面
     * @return 个人身份认证界面
     */
    @GetMapping(value = "/web/personalIdentityConfimPage")
    public String identityConfimPage() {
        return "u_renzheng1";
    }


}
