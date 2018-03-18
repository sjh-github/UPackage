package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalCenterSmallTaskController {

    /**
     * 个人任务里面的小任务界面
     * @return 个人任务里面的小任务界面
     */
    @GetMapping(value = "/web/personalCenter/smallTaskPage")
    public String personalCenterSmallTask() {
        return "u_workList1";
    }
}
