package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取面部数据接口实例
 */
@Service
public class GetFaceDataServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 获取接包人面部数据
     * @param email 邮箱
     * @return 面部数据/NULL
     */
    public String getReceiveTaskPersonalFaceData(String email) {
        if (email == null) {
            return null;
        }
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return null;
        }
        return receiveTask_personal.getPhotodata();
    }
}
