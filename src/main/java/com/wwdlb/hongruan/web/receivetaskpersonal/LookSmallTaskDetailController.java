package com.wwdlb.hongruan.web.receivetaskpersonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LookSmallTaskDetailController {

    /**
     * 查看小任务详情界面
     * @return 小任务详情界面
     */
    @GetMapping(value = "/web/smallTaskDetailPage")
    public String lookSmallTaskDetailPage() {
        return "workInfo";
    }

    /**
     * 查看所有小任务界面
     * @return 正在所有小任务界面
     */
    @GetMapping(value = "/web/smallTaskAllPage")
    public String lookSmallTaskAllPage() {
        return "workList";
    }

    /**
     * 查看正在进行小任务界面
     * @return 正在进行小任务界面
     */
    @GetMapping(value = "/web/smallTaskRunningPage")
    public String lookSmallTaskRunningPage() {
        return "workList1";
    }

    /**
     * 查看未开始小任务界面
     * @return 未开始小任务界面
     */
    @GetMapping(value = "/web/smallTaskRunnablePage")
    public String lookSmallTaskRunnablePage() {
        return "workList2";
    }
}
