package com.wwdlb.hongruan.web.providetaskpersonal;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.pojo.ReceivePersonAndSmallTaskNumPojo;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ReceiveCompanyManageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ReceiveSmallTaskPersonManageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ShowSmallTaskByProvidePersonEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(String.valueOf(ProvideTaskPersonalIndexController.this));

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;
	
	@Autowired
    private ReceiveSmallTaskPersonManageServiceImpl receiveSmallTaskPersonManageServiceImpl;

	@Autowired
    private ShowSmallTaskByProvidePersonEmailServiceImpl showSmallTaskByProvidePersonEmailServiceImpl;

    @GetMapping(value = "/web/indexPage/provideTaskPersonal")
    public String provideTaskPersonalPage(HttpServletRequest request, ModelMap modelMap) {
        httpSession = request.getSession();
        try {
                String email = (String) httpSession.getAttribute("email");
                logger.error("email:" + email);
                modelMap.addAttribute("email", email);
                modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
                modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
                modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
                modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
                modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
				
				//接包人员显示7条数据
				ArrayList<ReceivePersonAndSmallTaskNumPojo> receivePersonAndSmallTaskNumPojos = receiveSmallTaskPersonManageServiceImpl.getReceiveSmallTaskPersonalOrderBySmallTaskNum();
				if(receivePersonAndSmallTaskNumPojos != null) {
				    modelMap.addAttribute("bigReceivePersonAndSmallTaskNumPojos", receivePersonAndSmallTaskNumPojos.get(0));
                    receivePersonAndSmallTaskNumPojos.remove(0);
                    if (receivePersonAndSmallTaskNumPojos.size() > 0) {
                        ArrayList<ReceivePersonAndSmallTaskNumPojo> middleReceivePersonAndSmallTaskNumPojos = new ArrayList<>(3);
                        for (int i = 0; i < 3 && i < receivePersonAndSmallTaskNumPojos.size(); i++) {
                            middleReceivePersonAndSmallTaskNumPojos.add(receivePersonAndSmallTaskNumPojos.get(i));
                        }
                        modelMap.addAttribute("middleReceivePersonAndSmallTaskNumPojos", middleReceivePersonAndSmallTaskNumPojos);
                        receivePersonAndSmallTaskNumPojos.removeAll(middleReceivePersonAndSmallTaskNumPojos);
                    }
					modelMap.addAttribute("smallReceivePersonAndSmallTaskNumPojos", receivePersonAndSmallTaskNumPojos);
				}
				//接包公司显示
				/*modelMap.addAttribute("receiveCompany", receiveCompanyManageServiceImpl.getOneReceiveTaskCompany());*/
                //小任务显示
                ArrayList<SmallTask> runningSmallTaskList = showSmallTaskByProvidePersonEmailServiceImpl.getRunningSmallTaskByProvidePersonEmail(email);
                ArrayList<SmallTask> threeRunningSmalltaskList = new ArrayList<>(3);
                if (runningSmallTaskList.size() > 3) {
                   for (int i = 0; i < 3; i++) {
                       threeRunningSmalltaskList.add(runningSmallTaskList.get(i));
                   }
                } else {
                    threeRunningSmalltaskList = runningSmallTaskList;
                }
                modelMap.addAttribute("runningSmallTaskList", threeRunningSmalltaskList);
                modelMap.addAttribute("allSmallTaskNum", showSmallTaskByProvidePersonEmailServiceImpl.getAllSmallTaskByProvidePersonEmail(email).size());
                return "releaseTaskPersonalIndex";
        } catch (Exception e) {
            logger.error("error:" + e.getStackTrace() + ", :" + e.getMessage());
            return "redirect:http://115.159.71.92/hongruan/web/loginPage";
        }
    }
}
