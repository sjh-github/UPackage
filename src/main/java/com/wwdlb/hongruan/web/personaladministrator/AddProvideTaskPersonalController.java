package com.wwdlb.hongruan.web.personaladministrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 新增发包人界面
 */
@Controller
public class AddProvideTaskPersonalController {

    @GetMapping(value = "/web/provideTaskPersonalPage")
    public String provideTaskPersonalPage() {
        return "add_user_f";
    }
}
