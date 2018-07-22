package com.wwdlb.hongruan.web.providetaskpersonal;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ShowSmallTaskByProvidePersonEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.SearchBySmallTaskNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * 发包人搜索发布的小任务
 */
@Controller
public class SearchBySmallTaskNameAndProvidePersonController {
    @Autowired
    private SearchBySmallTaskNameServiceImpl searchBySmallTaskNameServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private ShowSmallTaskByProvidePersonEmailServiceImpl showSmallTaskByProvidePersonEmailServiceImpl;

    private HttpSession httpSession;


    @GetMapping(value = "/web/search/provideTaskPerson/smallTaskName")
    public String searchBySmallTaskName(@RequestParam String smallTaskName, ModelMap modelMap, HttpServletRequest request) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        ArrayList<SmallTask> allSmallTaskArrayList = showSmallTaskByProvidePersonEmailServiceImpl.getAllSmallTaskByProvidePersonEmail(email);
        if (allSmallTaskArrayList != null && smallTaskName != null && !smallTaskName.equals("")) {
            StringBuffer stringBuffer = new StringBuffer(smallTaskName);
            ArrayList<SmallTask> smallTaskArrayList = new ArrayList<>();
            System.out.println("allSmallTaskSize:" + allSmallTaskArrayList.size());
            for (SmallTask smallTask : allSmallTaskArrayList) {
                if (smallTask.getSmalltaskname().contains(stringBuffer)) {
                    smallTaskArrayList.add(smallTask);
                }
            }
            System.out.println("smallTaskArrayListSize:" + smallTaskArrayList.size());
            modelMap.addAttribute("allSmallTasks", smallTaskArrayList);
        }
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("showNum", false);
        return "r_workListSearch";
    }
}
