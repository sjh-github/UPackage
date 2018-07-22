package com.wwdlb.hongruan.web.receivetaskcompany;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyIdentityConfimController {
    /**
     * 公司身份认证界面
     * @return 公司身份认证界面
     */
    @GetMapping(value = "/web/companyIdentityConfimPage")
    public String identityConfimPage() {
        return "u_renzheng2";
    }
}
