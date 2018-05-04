package com.wwdlb.hongruan.web.personaladministrator;

import com.wwdlb.hongruan.model.IPWhitelist;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.personneladministrator.IPWhiteListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * IP白名单
 */
@Controller
public class IPWhiteListController {
    private HttpSession httpSession;

    @Autowired
    private IPWhiteListServiceImpl ipWhiteListServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    /**
     * IP白名单界面
     */
    @GetMapping(value = "/web/IPWhiteList")
    public String IPWhiteListPage(@RequestParam(required = false) String result, ModelMap modelMap, @RequestParam(required = false)String updateResult,
                                  HttpServletRequest request) {
        httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("email");
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getPersonalAdministratorNameByEmail(email));
        if (result != null) {
            modelMap.addAttribute("result", result);
        }
        if (updateResult != null) {
            modelMap.addAttribute("updateResult", updateResult);
        }
        modelMap.addAttribute("ipWhiteList", ipWhiteListServiceImpl.getAllIPWhiteList());
        return "IPWhiteList";
    }

    /**
     * 更新IP白名单
     * @return 页面刷新
     */
    @GetMapping(value = "/web/update/IPWhiteList/{ipWhiteListID}")
    public String updateWhiteList(@PathVariable Integer ipWhiteListID, @RequestParam String startstartaddress,
                                  @RequestParam String endaddress, @RequestParam String remark) {
        IPWhitelist ipWhitelist = new IPWhitelist();
        ipWhitelist.setIpwhitelistid(ipWhiteListID);
        ipWhitelist.setStartaddress(startstartaddress);
        ipWhitelist.setEndaddress(endaddress);
        ipWhitelist.setRemark(remark);
        if (ipWhiteListServiceImpl.updateWhiteList(ipWhitelist)) {
            return "redirect:/web/IPWhiteList?updateResult=true";
        }
        return "redirect:/web/IPWhiteList?updateResult=false";
    }

    /**
     * 删除IP白名单
     * @param ipWhiteListID ip地址ID
     * @return 页面刷新
     */
    @GetMapping(value = "/web/delete/IPWhiteList")
    public String deleteWhiteList(@RequestParam Integer ipWhiteListID) {
        if (ipWhiteListServiceImpl.deleteWhiteList(ipWhiteListID)) {
            return "redirect:/web/IPWhiteList?result=true";
        }
        return "redirect:/web/IPWhiteList?result=false";
    }
}
