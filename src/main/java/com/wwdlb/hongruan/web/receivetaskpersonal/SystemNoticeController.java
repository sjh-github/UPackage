package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemNoticeController {

    /**
     * 系统消息界面映射
     * @return 系统消息界面
     */
    @GetMapping(value = "/web/systemNoticePage")
    public String systemNoticePage() {
        return "u_xiaoxi";
    }
}
