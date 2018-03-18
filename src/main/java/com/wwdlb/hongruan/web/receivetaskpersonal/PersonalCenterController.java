package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalCenterController {

    /**
     * 个人中心界面映射
     * @return 个人中心界面
     */
    @GetMapping(value = "/web/personalCenterPage")
    public String personalCenterPage() {
        return "u_index";
    }
}
