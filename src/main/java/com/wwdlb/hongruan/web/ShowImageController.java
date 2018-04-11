package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.service.serviceImpl.ShowImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ShowImageController {

    @Autowired
    private ShowImageServiceImpl showImageServiceImpl;

    private HttpSession httpSession;

    /**
     * 显示接包人身份证照片
     * @param httpServletResponse response
     */
    @GetMapping("/web/receiveTaskPersonalImage")
    public void showReceiveTaskPersonalPhoto(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpSession = httpServletRequest.getSession();
        try {
            String email = (String) httpSession.getAttribute("email");
            showImageServiceImpl.showReceiveTaskPersonalPhoto(httpServletResponse, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 显示发包人身份证照片
     * @param httpServletResponse response
     */
    @GetMapping("/web/provideTaskPersonalImage")
    public void showProvideTaskPersonalPhoto(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpSession = httpServletRequest.getSession();
        try {
            String email = (String) httpSession.getAttribute("email");
            showImageServiceImpl.showProvideTaskPersonalPhoto(httpServletResponse, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
     * 显示人事管理员身份证照片
     * @param httpServletResponse response
     */
    @GetMapping("/web/personnelAdministratorImage")
    public void showPersonnelAdministratorPhotoImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpSession = httpServletRequest.getSession();
        try {
            String email = (String) httpSession.getAttribute("email");
            showImageServiceImpl.showPersonnelAdministratorPhoto(httpServletResponse, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
