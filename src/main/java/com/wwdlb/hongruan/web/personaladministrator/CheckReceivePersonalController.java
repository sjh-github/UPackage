package com.wwdlb.hongruan.web.personaladministrator;

import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.personneladministrator.CheckReceiveTaskPersonServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.personneladministrator.DelReceiveTaskPersonServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.personneladministrator.ReceiveTaskPersonManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 接包人审核界面
 */
@Controller
public class CheckReceivePersonalController {
    @Value("${my.per_page}")
    private int per_page;

    private HttpSession httpSession;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private ReceiveTaskPersonManageServiceImpl receiveTaskPersonManageServiceImpl;

    @Autowired
    private CheckReceiveTaskPersonServiceImpl checkReceiveTaskPersonServiceImpl;

    @Autowired
    private DelReceiveTaskPersonServiceImpl delReceiveTaskPersonServiceImpl;

    /**
     * 未审核接包人界面
     * @return 未审核接包人界面
     */
    @GetMapping(value = "/web/receiveTaskPersonal/no")
    public String noCheckReceiveTaskPersonal(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false)String result,
                                             @RequestParam(required = false)Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getPersonalAdministratorNameByEmail(email));
        modelMap.addAttribute("noCheckPersonList", receiveTaskPersonManageServiceImpl.noCheckReceiveTaskPerson(page, per_page));
        modelMap.addAttribute("nowPage", page);
        if (result != null) {
            modelMap.addAttribute("result", result);
        }
        return "u_user_list_sh";
    }

    /**
     * 已审核接包人界面
     */
    @GetMapping(value = "/web/receiveTaskPersonal/yes")
    public String yesCheckReceiveTaskPersonal(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false) String result,
                                              @RequestParam(required = false)Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getPersonalAdministratorNameByEmail(email));
        modelMap.addAttribute("yesCheckPersonList", receiveTaskPersonManageServiceImpl.yesCheckReceiveTaskPerson(page, per_page));
        modelMap.addAttribute("nowPage", page);
        if (result != null) {
            modelMap.addAttribute("result", result);
        }
        return "u_user_list_tg";
    }

    /**
     * 审核通过接包人
     * @param email 接包人邮箱
     * @return 刷新界面
     */
    @GetMapping(value = "/web/receivePerson/check/yes")
    public String passReceiveTaskPerson(@RequestParam String email) {
        if (checkReceiveTaskPersonServiceImpl.passReceiveTaskPerson(email)) {
            return "redirect:/web/receiveTaskPersonal/no?result=true";
        } else {
            return "redirect:/web/receiveTaskPersonal/no?result=false";
        }
    }

    /**
     * 审核不通过接包人
     * @param email 接包人邮箱
     * @return 刷新界面
     */
    @GetMapping(value = "/web/receivePerson/check/no")
    public String noPassReceiveTaskPerson(@RequestParam String email) {
        if (checkReceiveTaskPersonServiceImpl.noPassReceiveTaskPerson(email)) {
            return "redirect:/web/receiveTaskPersonal/no?result=true";
        } else {
            return "redirect:/web/receiveTaskPersonal/no?result=false";
        }
    }

    /**
     * 删除接包人
     * @param email 接包人邮箱
     * @return 删除结果
     */
    @GetMapping(value = "/web/receivePerson/del")
    public String delPassReceiveTaskPerson(@RequestParam String email) {
        if (delReceiveTaskPersonServiceImpl.delReceiveTaskPerson(email)) {
            return "redirect:/web/receiveTaskPersonal/yes?result=true";
        } else {
            return "redirect:/web/receiveTaskPersonal/yes?result=false";
        }
    }
}
