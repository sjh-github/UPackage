package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 接包人搜索已接小任务
 */
@Controller
public class SearchBySmallTaskNameAndReceivePersonController {
    @Autowired
    private SearchBySmallTaskNameServiceImpl searchBySmallTaskNameServiceImpl;

    @Autowired
    private GetReceiveTaskPersonalServiceImpl getReceiveTaskPersonalServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private LookSmallTaskServiceImpl lookSmallTaskServiceImpl;

    private HttpSession httpSession;

    @Autowired
    private GetSignTimeServiceImpl getSignTimeServiceImpl;

    @GetMapping(value = "/web/search/smallTaskName")
    public String searchBySmallTaskName(@RequestParam String smallTaskName, ModelMap modelMap, HttpServletRequest request) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ArrayList<SmallTask> allSmallTaskList = lookSmallTaskServiceImpl.findAllSmallTaskByEmail(email);
        if (allSmallTaskList != null && smallTaskName != null && !smallTaskName.equals("")) {
            ArrayList<SmallTask> smallTaskArrayList = new ArrayList<>();
            StringBuffer stringBuffer = new StringBuffer(smallTaskName);
            for (SmallTask smallTask : allSmallTaskList) {
                if (smallTask.getSmalltaskname().contains(stringBuffer)) {
                    smallTaskArrayList.add(smallTask);
                }
            }
            modelMap.addAttribute("AllSmallTasks", smallTaskArrayList);
        }
        ReceiveTask_Personal receiveTask_personal = getReceiveTaskPersonalServiceImpl.getReceiveTaskPersonalByEmail(email);
        if (receiveTask_personal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int nowyear = Integer.parseInt(simpleDateFormat.format(date));
            int birthyear = receiveTask_personal.getBirthyear();
            modelMap.addAttribute("age", nowyear - birthyear);
        }
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getReceiveTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("showNum",false);
        modelMap.addAttribute("signInTime", getSignTimeServiceImpl.getSignInTime(email));
        modelMap.addAttribute("signOutTime", getSignTimeServiceImpl.getSignOutTime(email));
        return "workListSearch";
    }
}
