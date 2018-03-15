package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.service.serviceImpl.ShowImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class ShowImageController {

    @Autowired
    private ShowImageServiceImpl showImageServiceImpl;

    private HttpSession httpSession;

    /**
     * 显示接包人身份证照片
     * @param httpServletResponse response
     * @throws IOException IO异常
     * @throws FileNotFoundException 文件未找到异常
     */
    @GetMapping("/web/receiveTaskPersonalImage")
    public void showReceiveTaskPersonalPhoto(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileNotFoundException {
        httpSession = httpServletRequest.getSession();
        try {
            String email = (String) httpSession.getAttribute("email");
            showImageServiceImpl.showReceiveTaskPersonalPhoto(httpServletResponse, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
