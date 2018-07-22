package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.IPWhitelistMapper;
import com.wwdlb.hongruan.model.IPWhitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * IP地址检测服务实例
 */
@Service
public class IPCheckServiceImpl {
    @Autowired
    private IPWhitelistMapper ipWhitelistMapper;

    /**
     * 是否在IP白名单里
     * @param ipAddress IP地址
     * @return true/false
     */
    public boolean isInWhiteList(String ipAddress) {
        ArrayList<IPWhitelist> allIPWhiteLists = ipWhitelistMapper.selectAll();
        if (allIPWhiteLists == null) {
            return true;
        }
        for (IPWhitelist ipWhitelist : allIPWhiteLists) {
            if (ipAddress.compareTo(ipWhitelist.getStartaddress()) >= 0 && ipAddress.compareTo(ipWhitelist.getEndaddress()) <= 0) {
                return true;
            }
        }
        return false;
    }
}
