package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountSecurityController {

    /**
     * 账号安全界面映射
     * @return 账号安全界面
     */
    @GetMapping(value = "/web/countSecurityPage")
    public String countSecurityPage() {
        return "u_safe";
    }
}
