package com.wwdlb.hongruan.web.providetaskpersonal;

import com.wwdlb.hongruan.service.serviceImpl.GetNameByEmailServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.ProvideTaskServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.NumOfIndexPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProvideTaskController {

    private HttpSession httpSession;

    @Autowired
    private NumOfIndexPageServiceImpl numOfIndexPageServiceImpl;

    @Autowired
    private GetNameByEmailServiceImpl getNameByEmailServiceImpl;

	@Autowired
	private ProvideTaskServiceImpl provideTaskServiceImpl;
	
    @GetMapping(value = "/web/taskPage")
    public String releaseTaskPage(HttpServletRequest httpServletRequest, ModelMap modelMap, @RequestParam(required = false) String result) {
        httpSession = httpServletRequest.getSession();
        String email = (String) httpSession.getAttribute("email");
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("name", getNameByEmailServiceImpl.getProvideTaskPersonalNameByEmail(email));
        modelMap.addAttribute("numOfReceiveTaskPersonal", numOfIndexPageServiceImpl.getNumOfReceiveTaskPersonal());
        modelMap.addAttribute("numOfReceiveTaskCompany", numOfIndexPageServiceImpl.getNumOfReceiveTaskCompany());
        modelMap.addAttribute("numOfHaveFinishedSmallTask", numOfIndexPageServiceImpl.getNumOfFinishedSmallTask());
        modelMap.addAttribute("numOfSmallTask", numOfIndexPageServiceImpl.getNumOfSmallTask());
		if(result != null) {
			modelMap.addAttribute("result", result);
		}
        return "releaseTask";
    }
	
	@PostMapping(value = "/web/task")
	public String provideTask(@RequestParam String taskName, @RequestParam(required = false) String taskDetail, @RequestParam Integer safetyGrade, @RequestParam Integer priority,
							@RequestParam String startTime, @RequestParam String endTime) {
		if(provideTaskServiceImpl.provideTask(taskName, taskDetail, safetyGrade, priority, startTime, endTime) != null) {
			return "redirect:/web/taskPage?result=true";
		} else {
			return "redirect:/web/taskPage?result=false";
		}
	}
}
