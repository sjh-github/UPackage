package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 注册服务接口实例
 */
@Service
public class RegisiterServiceImpl {

    @Value("${my.receiveTaskPersonalPhotoFileAddress}")
    private String receiveTaskPersonalPhotoFileAddress;

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 接包人员注册
     * @param email 邮箱
     * @param password 密码
     * @param name 姓名
     * @param gender 性别：男/女
     * @param birthyear 出生年份
     * @param birthmonth 出生月份
     * @param birthday 出生日期
     * @param idcard 身份证号码
     * @param phone 手机号
     * @param multipartFile 身份证照片信息
     * @param photodata 面部数据
     * @return true:注册成功，false:该邮箱已经被注册
     */
    @Transactional
    public boolean regisiterReceiveTaskPersonal(String email, String password, String name,
                                             String gender, Integer birthyear, Integer birthmonth,
                                             Integer birthday, String idcard, String phone,
                                             MultipartFile multipartFile, String photodata) {
        //查找该邮箱是否已被注册
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            receiveTask_personal = new ReceiveTask_Personal();
            receiveTask_personal.setEmail(email);
            receiveTask_personal.setPassword(password);
            receiveTask_personal.setName(name);
            if ("男".equals(gender)) {
                receiveTask_personal.setGender(0);
            } else {
                receiveTask_personal.setGender(1);
            }
            receiveTask_personal.setBirthyear(birthyear);
            receiveTask_personal.setBirthmonth(birthmonth);
            receiveTask_personal.setBirthday(birthday);
            receiveTask_personal.setIdcard(idcard);
            receiveTask_personal.setPhone(phone);
            //文件上传
            if (!multipartFile.isEmpty()) {
                int pointIndex = multipartFile.getOriginalFilename().lastIndexOf('.');
                String fileLastName = multipartFile.getOriginalFilename().substring(pointIndex,multipartFile.getOriginalFilename().length());
                new Thread(() -> {
                    try {
                        File file = new File(receiveTaskPersonalPhotoFileAddress + email + fileLastName);
                        if (file.exists()) {
                            file.delete();
                        }
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                        bufferedOutputStream.write(multipartFile.getBytes());
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                receiveTask_personal.setIdfile(receiveTaskPersonalPhotoFileAddress + email + fileLastName);
            }
            receiveTask_personal.setPhotodata(photodata);
            receiveTask_personal.setHavechecked("F");
            receiveTask_personalMapper.insertSelective(receiveTask_personal);
            return true;
        } else {    //该邮箱已被注册
            return false;
        }
    }
}
