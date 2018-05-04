package com.wwdlb.hongruan.web.providetaskpersonal;

import com.wwdlb.hongruan.pojo.ReceivePersonAndSmallTaskNumPojo;
import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ProvideSmallTaskServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ReceiveSmallTaskPersonManageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.TaskManageServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProvideSmallTaskController {

    private HttpSession httpSession;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

	@Autowired 
	private TaskManageServiceImpl taskManageServiceImpl;
	
	@Autowired
	private ProvideSmallTaskServiceImpl provideSmallTaskServiceImpl;

	@Autowired
	private ReceiveSmallTaskPersonManageServiceImpl receiveSmallTaskPersonManageServiceImpl;

    @GetMapping(value = "/web/smallTaskPage")
    public String newSmallTaskPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false) String result) {
        httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
		ArrayList<String> allTaskName = taskManageServiceImpl.getAllTaskName();
		if(allTaskName != null) {
			modelMap.addAttribute("allTaskName", allTaskName);
		}
		ArrayList<ReceivePersonAndSmallTaskNumPojo> receivePersonAndRunningSmallTaskList =  receiveSmallTaskPersonManageServiceImpl.receiveSmallTaskSurvey();
		modelMap.addAttribute("receivePersonAndRunningSmallTaskList", receivePersonAndRunningSmallTaskList);
		if(result != null) {
			modelMap.addAttribute("result", result);
		}
        return "demand";
    }
	
	@PostMapping(value = "/web/smallTask")
	public String provideSmallTask(@RequestParam String taskName, @RequestParam String smallTaskName,
									@RequestParam String smallTaskDetail, @RequestParam String endTime, 
									@RequestParam String receiveSmallTaskEmail, @RequestParam(required = false) Integer numberProgress, 
									@RequestParam(required = false) ArrayList<String> customProgressArrayList, HttpServletRequest request) {
		httpSession = request.getSession();
		String email = (String) httpSession.getAttribute("email");
		if(provideSmallTaskServiceImpl.provideSmallTask(email, taskName, smallTaskName, smallTaskDetail, endTime,
			receiveSmallTaskEmail, numberProgress, customProgressArrayList) != null) {
			return "redirect:/web/smallTaskPage?result=true";
		} else {
			return "redirect:/web/smallTaskPage?result=false";
		}
		/*return "taskName:" + taskName + ",smallTaskName:" +smallTaskName + ",smallTaskDetail:" + smallTaskDetail + ", endTime:" + endTime + ",receiveSmallTaskEmail:"
				+ receiveSmallTaskEmail + ",numberProgress:" + numberProgress + ",customProgress:" + customProgressArrayList.toString();*/
	}
}
