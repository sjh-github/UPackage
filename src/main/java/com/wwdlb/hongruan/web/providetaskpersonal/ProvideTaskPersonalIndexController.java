package com.wwdlb.hongruan.web.providetaskpersonal;

import com.wwdlb.hongruan.pojo.ReceivePersonAndSmallTaskNumPojo;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ReceiveSmallTaskPersonManageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProvideTaskPersonalIndexController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;
	
	@Autowired
    private ReceiveSmallTaskPersonManageServiceImpl receiveSmallTaskPersonManageServiceImpl;

    @GetMapping(value = "/web/indexPage/provideTaskPersonal")
    public String provideTaskPersonalPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        try {
            String role = (String) httpSession.getAttribute("role");
            if (role.equals(LoginServiceImpl.ProvideTaskPersonal)) {
                String email = (String) httpSession.getAttribute("email");
                modelMap.addAttribute("email", email);
                modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
                modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
                modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
                modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
                modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
				
				//接包人员显示7条数据
				ArrayList<ReceivePersonAndSmallTaskNumPojo> receivePersonAndSmallTaskNumPojo = receiveSmallTaskPersonManageServiceImpl.getReceiveSmallTaskPersonalOrderBySmallTaskNum();
				if(receivePersonAndSmallTaskNumPojo != null) {
					modelMap.addAttribute("receivePersonAndSmallTaskNumPojo", receivePersonAndSmallTaskNumPojo);
				}
				//接包公司显示
				
				
                return "releaseTaskPersonalIndex";
            } else {
                return "redirect:/web/loginPage";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/web/loginPage";
        }
    }
}
