package com.wwdlb.hongruan.aspect;

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
public class WEBReceiveTaskPersonalSessionCheckAspect {
    @Pointcut("execution(public * com.wwdlb.hongruan.web..receivetaskpersonal..*.*(..))")
    public void webSessionCheck(){}

    @Before("webSessionCheck()")
    public void doBefore(JoinPoint joinPoint) throws IOException {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession httpSession = request.getSession();
        try {
            String role = (String) httpSession.getAttribute("role");
            if (role == null) {
                response.sendRedirect("loginPage");
            }
        } catch (Exception e) {
            response.sendRedirect("loginPage");
        }
    }
}
