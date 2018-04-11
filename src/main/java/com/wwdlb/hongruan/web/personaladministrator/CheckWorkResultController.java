package com.wwdlb.hongruan.web.personaladministrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 审核小任务详情
 */
@Controller
public class CheckWorkResultController {

    @GetMapping(value = "/web/smallTaskDetailCheck")
    public String checkWorkResult() {
        return "workInfo_1";
    }
}
