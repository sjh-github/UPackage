package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class ShowImageServiceImpl {

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 显示接包个人身份证照片
     * @param httpServletResponse response
     * @param email 接包人邮箱
     */
    public void showReceiveTaskPersonalPhoto(HttpServletResponse httpServletResponse, String email) {
        BufferedInputStream bis = null;
        int length;
        String userPhoto = "";
        try {
            ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
            if (receiveTask_personal != null) {
                userPhoto = receiveTask_personal.getIdfile();
                if (userPhoto.equals("")) {
                    return;
                }
            }
            bis = new BufferedInputStream(new FileInputStream(new File(userPhoto)));
            byte[] bytes = new byte[1024*1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024*1024);
            while((length = bis.read(bytes))!=-1){
                out.write(bytes,0,length);
            }
            bis.close();
            ServletOutputStream sevletOutputStream = httpServletResponse.getOutputStream();
            out.writeTo(sevletOutputStream);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
