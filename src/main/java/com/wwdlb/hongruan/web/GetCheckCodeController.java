package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.Info.Info;
import com.wwdlb.hongruan.service.serviceImpl.GetCheckCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GetCheckCodeController {
    private HttpSession session;

    @Autowired
    private GetCheckCodeServiceImpl getCheckCodeServiceImpl;

    @GetMapping(value = "/web/checkCode")
    public String getCheckCode(HttpServletRequest request, @RequestParam String phone) {
        session = request.getSession();
        String email = (String) session.getAttribute("email");
        Info.checkCode = getCheckCodeServiceImpl.getCheckCode(email);
        return "redirect:/web/changePhonePage?phone=" + phone;
    }
}
