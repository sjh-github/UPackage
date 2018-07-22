package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.wwdlb.hongruan.mapper.ProvideTask_PersonalMapper;
import com.wwdlb.hongruan.model.ProvideTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 新增发包人服务实例
 */
@Transactional
@Service
public class AddProvideSmallTaskServiceImpl {
    @Autowired
    private ProvideTask_PersonalMapper provideTask_personalMapper;

    public boolean addProvideSmallTaskPersonal(String email, String password, String name, Integer gender,
                                               Integer birthyear, Integer birthmonth, Integer birthday,
                                               String idcard, String phone, byte[] photoData) {
        //查找该邮箱是否已被注册
        ProvideTask_Personal provideTask_personal = provideTask_personalMapper.selectByPrimaryKey(email);
        if (provideTask_personal == null) {
            provideTask_personal = new ProvideTask_Personal();
            provideTask_personal.setEmail(email);
            provideTask_personal.setPassword(password);
            provideTask_personal.setName(name);
            if ("男".equals(gender)) {
                provideTask_personal.setGender(0);
            } else {
                provideTask_personal.setGender(1);
            }
            if (birthyear != null) {
                if (birthyear > (new Date().getYear())) {
                    return false;
                }
                provideTask_personal.setBirthyear(birthyear);
            }
            if (birthmonth != null) {
                if (birthmonth < 1 || birthmonth > 12) {
                    return false;
                }
                provideTask_personal.setBirthmonth(birthmonth);
            }
            if (birthday != null) {
                if (birthday < 1 || birthday > 31) {
                    return false;
                }
                provideTask_personal.setBirthday(birthday);
            }
            if (idcard != null) {
                if (idcard.length() != 18) {
                    return false;
                }
                provideTask_personal.setIdcard(idcard);
            }
            if (phone != null) {
                provideTask_personal.setPhone(phone);
            }
            if (photoData != null) {
                provideTask_personal.setPhotodata(photoData);
            }
            provideTask_personal.setHavechecked("T");
            provideTask_personalMapper.insertSelective(provideTask_personal);
            return true;
        } else {    //该邮箱已被注册
            return false;
        }
    }
}
