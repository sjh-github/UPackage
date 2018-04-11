package com.wwdlb.hongruan.api.providetask;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.pojo.SmallTaskPojo;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.SmallTaskManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Android小任务管理
 */
@RestController
public class SmallTaskManageAction {

    private HttpSession httpSession;

    @Autowired
    private SmallTaskManageServiceImpl smallTaskManageServiceImpl;

    /**
     * 获取项目下属所有小任务
     * @param taskID 项目ID
     * @return 小任务列表/NULL
     */
    @GetMapping(value = "/api/smalltask")
    public ArrayList<SmallTaskPojo> getAllSmallTaskByTaskID(@RequestParam int taskID) {
        return smallTaskManageServiceImpl.getAllSmallTaskByTaskID(taskID);
    }

    /**
     * 删除小任务
     * @param smallTaskID 小任务ID
     * @return true/false
     */
    @DeleteMapping(value = "/api/smallTask/{smallTaskID}")
    public boolean deleteTask(@PathVariable Integer smallTaskID, HttpServletRequest request) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        return smallTaskManageServiceImpl.deleteSmallTask(email, smallTaskID);
    }
}
