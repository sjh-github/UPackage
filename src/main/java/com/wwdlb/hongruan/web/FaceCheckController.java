package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.Info.Info;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.GetReceiveTaskPersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FaceCheckController {


    @GetMapping(value = "/web/faceCheckPage")
    public String faceCheckPage(@RequestParam(required = false)String checkResult, ModelMap modelMap) {
        if (checkResult != null) {
            modelMap.addAttribute("checkResult", checkResult);
        }
        return "face";
    }

    @GetMapping(value = "/web/faceCheck")
    public String faceCheck(@RequestParam Double checkResult, @RequestParam(required = false) Double accuracy) {
        if (accuracy == null) {
            accuracy = Info.accuracy;
            System.out.println(checkResult + "," + accuracy);
        }
        if (checkResult >= accuracy) {
            return "redirect:/web/indexPage/receiveTaskPersonal";
        } else {
            return "redirect:/web/faceCheckPage?checkResult=false";
        }
    }
}
