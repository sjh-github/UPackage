package com.wwdlb.hongruan.web.personaladministrator;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.personneladministrator.SearchByReceiveTaskNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SearchByReceiveTaskNameController {
    @Autowired
    private SearchByReceiveTaskNameServiceImpl searchByReceiveTaskNameServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    private HttpSession httpSession;

    /**
     * 搜索已审核人员
     * @param receiveTaskName 已审核人员姓名
     * @return 已审核界面
     */
    @GetMapping(value = "/web/search/receiveTaskName/checked")
    public String searchCheckedByReceiveTaskName(@RequestParam String receiveTaskName, ModelMap modelMap, HttpServletRequest request) {
        ArrayList<ReceiveTask_Personal> haveCheckedReceiveTaskLists = searchByReceiveTaskNameServiceImpl.searchhavePassReceivePerson(receiveTaskName);
        modelMap.addAttribute("yesCheckPersonList", haveCheckedReceiveTaskLists);
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getPersonalAdministratorNameByEmail(email));
        return "u_user_list_tg2";
    }

    /**
     * 搜索未审核人员
     * @param receiveTaskName 未审核人员姓名
     * @return 未审核界面
     */
    @GetMapping(value = "/web/search/receiveTaskName/noChecked")
    public String searchNotCheckedByReceiveTaskName(@RequestParam String receiveTaskName, ModelMap modelMap, HttpServletRequest request) {
        ArrayList<ReceiveTask_Personal> haveCheckedReceiveTaskLists = searchByReceiveTaskNameServiceImpl.searchNotPassReceivePerson(receiveTaskName);
        modelMap.addAttribute("noCheckPersonList", haveCheckedReceiveTaskLists);
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getPersonalAdministratorNameByEmail(email));
        return "u_user_list_sh2";
    }
}
