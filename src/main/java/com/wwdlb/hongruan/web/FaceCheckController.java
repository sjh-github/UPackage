package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.Info.Info;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        accuracy = Info.accuracy;
        if (checkResult >= accuracy) {
            return "redirect:/web/indexPage/receiveTaskPersonal";
        } else {
            return "redirect:/web/faceCheckPage?checkResult=false";
        }
    }
}
