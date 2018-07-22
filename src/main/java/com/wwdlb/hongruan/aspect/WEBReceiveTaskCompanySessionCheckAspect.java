package com.wwdlb.hongruan.aspect;

import com.wwdlb.hongruan.service.serviceImpl.LoginServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Aspect
@Component
public class WEBReceiveTaskCompanySessionCheckAspect {
    @Pointcut("execution(public * com.wwdlb.hongruan.web..receivetaskcompany..*.*(..))")
    public void webSessionCheck(){}

    @Before("webSessionCheck()")
    public void doBefore(JoinPoint joinPoint) throws IOException {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession httpSession = request.getSession();
        try {
            response.setHeader("Cache-Control","no-cache"); //不对页面进行缓存，再次访问时将从服务器重新获取最新版本
            response.setHeader("Cache-Control","no-store"); //任何情况下都不缓存页面
            response.setDateHeader("Expires", 0); //使缓存过期
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 向后兼容
            String role = (String) httpSession.getAttribute("role");
            if (role == null) {
                response.sendRedirect("http://115.159.71.92/hongruan/web/loginPage");
            }
        } catch (Exception e) {
            response.sendRedirect("http://115.159.71.92/hongruan/web/loginPage");
        }
    }
}
