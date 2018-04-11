package com.wwdlb.hongruan.web.personaladministrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 接包人审核界面
 */
@Controller
public class CheckReceivePersonalController {
    /**
     * 未审核接包人
     * @return 未审核接包人界面
     */
    @GetMapping(value = "/web/receiveTaskPersonal/no")
    public String noCheckReceiveTaskPersonal() {
        return "u_user_list_sh";
    }

    @GetMapping(value = "/web/receiveTaskPersonal/yes")
    public String yesCheckReceiveTaskPersonal() {
        return "u_user_list_tg";
    }
}
