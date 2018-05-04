package com.wwdlb.hongruan.web;

import com.wwdlb.hongruan.service.serviceImpl.ShowImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 显示接包人身份证照片
     * @param email 接包人邮箱
     * @param httpServletResponse response
     */
    @GetMapping("/web/receiveSmallTaskPersonalImage")
    public void showReceiveTaskPersonalPhotoByEmail(@RequestParam String email, HttpServletResponse httpServletResponse) {
        showImageServiceImpl.showReceiveTaskPersonalPhoto(httpServletResponse, email);
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
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 显示接包公司照片
     * @param httpServletResponse response
     * @param email 接包公司email
     */
   /* @GetMapping("/web/receiveTaskCompany")
    public void showPersonnelAdministratorPhotoImage(HttpServletResponse httpServletResponse, @RequestParam String email) {
        showImageServiceImpl.showPersonnelAdministratorPhoto(httpServletResponse, email);
    }*/
}
