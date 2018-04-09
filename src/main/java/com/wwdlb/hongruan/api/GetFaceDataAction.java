package com.wwdlb.hongruan.api;

import com.wwdlb.hongruan.service.serviceImpl.GetFaceDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取面部数据
 */
@RestController
public class GetFaceDataAction {
    @Autowired
    private GetFaceDataServiceImpl faceDataServiceImpl;

    /**
     * 获取接包人面部数据
     * @param email 邮箱
     * @return 面部数据/NULL/""
     */
    @GetMapping(value = "/api/receiveTaskPersonalFaceData")
    public String getReceiveTaskPersonalFaceData(String email) {
        return faceDataServiceImpl.getReceiveTaskPersonalFaceData(email);
    }
}
