package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.Info.Info;
import com.wwdlb.hongruan.mapper.PersonnelAdministratorMapper;
import com.wwdlb.hongruan.mapper.ProvideTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_CompanyMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.PersonnelAdministrator;
import com.wwdlb.hongruan.model.ProvideTask_Personal;
import com.wwdlb.hongruan.model.ReceiveTask_Company;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class ShowImageServiceImpl {
	@Value("${my.defaultPhotoImageAddress}")
    private String defaultPhotoImageAddress;

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private ProvideTask_PersonalMapper provideTask_personalMapper;
	
	@Autowired
	private PersonnelAdministratorMapper personnelAdministratorMapper;

	/*@Autowired
    private ReceiveTask_CompanyMapper receiveTask_companyMapper;*/

    /**
     * 显示接包个人身份证照片
     * @param httpServletResponse response
     * @param email 接包人邮箱
     */
    public void showReceiveTaskPersonalPhoto(HttpServletResponse httpServletResponse, String email) {
        File file;
        BufferedInputStream bis = null;
        int length;
        String userPhoto;
        if (email == null) {
            userPhoto = Info.basePhotoAddress;
        } else {
            try {
                ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
                //存在该接包人
                if (receiveTask_personal != null) {
                    userPhoto = receiveTask_personal.getIdfile();
                    if (userPhoto == null || userPhoto.equals("")) {
                        //显示默认照片
                        userPhoto = defaultPhotoImageAddress;
                    } else {
                        file = new File(userPhoto);
                        if (!file.exists()) {
                            userPhoto = defaultPhotoImageAddress;
                        }
                    }
                } else {
                    userPhoto = Info.basePhotoAddress;
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

    /**
     * 显示发包个人身份证照片
     * @param httpServletResponse response
     * @param email 发包人邮箱
     */
    public void showProvideTaskPersonalPhoto(HttpServletResponse httpServletResponse, String email) {
        BufferedInputStream bis = null;
        int length;
        String userPhoto;
        if (email == null) {
            userPhoto = Info.basePhotoAddress;
        } else {
            try {
                ProvideTask_Personal provideTask_personal = provideTask_personalMapper.selectByPrimaryKey(email);
                if (provideTask_personal != null) {
                    userPhoto = provideTask_personal.getIdfile();
                    //显示默认照片
                    if (userPhoto == null || userPhoto.equals("")) {
                        userPhoto = defaultPhotoImageAddress;
                    } else {
                        File file = new File(userPhoto);
                        if (!file.exists()) {
                            userPhoto = defaultPhotoImageAddress;
                        }
                    }
                } else {
                    userPhoto = Info.basePhotoAddress;
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
	
	/**
     * 显示管理员个人身份证照片
     * @param httpServletResponse response
     * @param email 管理员邮箱
     */
    public void showPersonnelAdministratorPhoto(HttpServletResponse httpServletResponse, String email) {
        BufferedInputStream bis = null;
        int length;
        String userPhoto;
        if (email == null) {
            userPhoto = Info.basePhotoAddress;
        } else {
            try {
                PersonnelAdministrator personnelAdministrator = personnelAdministratorMapper.selectByPrimaryKey(email);
                if (personnelAdministrator != null) {
                    userPhoto = personnelAdministrator.getIdfile();
                    //显示默认照片
                    if (userPhoto == null || userPhoto.equals("")) {
                        userPhoto = defaultPhotoImageAddress;
                    } else {
                        System.out.println("照片：" + userPhoto);
                        File file = new File(userPhoto);
                        if (!file.exists()) {
                            userPhoto = defaultPhotoImageAddress;
                        }
                    }
                } else {
                    userPhoto = Info.basePhotoAddress;
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

    /**
     * 显示接包公司照片
     *//*
    public void showReceiveTaskCompanyPhoto(HttpServletResponse httpServletResponse, String email) {
        BufferedInputStream bis = null;
        int length;
        String userPhoto = "";
        try {
            ReceiveTask_Company receiveTask_company = receiveTask_companyMapper.selectByPrimaryKey(email);
            if (receiveTask_company != null) {
                userPhoto = personnelAdministrator.getIdfile();
                //显示默认照片
                if (userPhoto.equals("")) {
                    userPhoto = defaultPhotoImageAddress;
                } else {
                    File file = new File(userPhoto);
                    if (!file.exists()) {
                        userPhoto = defaultPhotoImageAddress;
                    }
                }
            } else {
                return;
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
    }*/

    public void showImage(HttpServletResponse httpServletResponse, String address) throws IOException {
            if (address == null) {
                address = Info.basePhotoAddress;
            }
            BufferedInputStream bis = null;
            int length;
            File file = new File(address);
            if (!file.exists()) {
                file = new File(Info.basePhotoAddress);
            }
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            byte[] bytes = new byte[1024*1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024*1024);
            while((length = bis.read(bytes))!=-1){
                out.write(bytes,0,length);
            }
            bis.close();
            ServletOutputStream sevletOutputStream = httpServletResponse.getOutputStream();
            out.writeTo(sevletOutputStream);
            out.flush();
    }
}
