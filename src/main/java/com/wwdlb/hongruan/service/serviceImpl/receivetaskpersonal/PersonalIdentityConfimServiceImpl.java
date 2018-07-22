package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.Info.Info;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 个人信息认证服务实例
 */
@Service
@Transactional
public class PersonalIdentityConfimServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    public boolean personalIdentityConfim(String email, String name, String idCard,
                                          MultipartFile topIDFile, MultipartFile downIDFile) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return false;
        } else {
            System.out.println(idCard);
            receiveTask_personal.setEmail(email);
            receiveTask_personal.setName(name);
            receiveTask_personal.setHavechecked("F");
            receiveTask_personal.setIdcard(idCard);
                    if (!topIDFile.isEmpty()) {
                        int pointIndex = topIDFile.getOriginalFilename().lastIndexOf('.');
                        String fileLastName = topIDFile.getOriginalFilename().substring(pointIndex,topIDFile.getOriginalFilename().length());
                        File topID = new File(Info.receiveTaskPersonPhotoBaseAddress + "top_" + email + fileLastName);
                        BufferedOutputStream bufferedOutputStream = null;
                        try {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(topID));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.write(topIDFile.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        receiveTask_personal.setIdfile(Info.receiveTaskPersonPhotoBaseAddress + "top_" + email + fileLastName);
                    }
                    if (!downIDFile.isEmpty()) {
                        int pointIndex = downIDFile.getOriginalFilename().lastIndexOf('.');
                        String fileLastName = downIDFile.getOriginalFilename().substring(pointIndex,downIDFile.getOriginalFilename().length());
                        File downID = new File(Info.receiveTaskPersonPhotoBaseAddress +  "down_" +email + fileLastName);
                        BufferedOutputStream bufferedOutputStream = null;
                        try {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(downID));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.write(downIDFile.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            receiveTask_personalMapper.updateByPrimaryKeySelective(receiveTask_personal);
        }
        return true;
    }
}
