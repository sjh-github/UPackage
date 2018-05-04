package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.wwdlb.hongruan.mapper.IPWhitelistMapper;
import com.wwdlb.hongruan.model.IPWhitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * IP地址白名单设置
 */
@Service
public class IPWhiteListServiceImpl {
    @Autowired
    private IPWhitelistMapper ipWhitelistMapper;

    public ArrayList<IPWhitelist> getAllIPWhiteList() {
        return ipWhitelistMapper.selectAll();
    }

    /**
     * 更新IP白名单
     * @param ipWhitelist ip地址
     * @return 更新结果
     */
    public boolean updateWhiteList(IPWhitelist ipWhitelist) {
        if (ipWhitelist != null) {
            IPWhitelist ipWhitelistOld = ipWhitelistMapper.selectByPrimaryKey(ipWhitelist.getIpwhitelistid());
            if (ipWhitelistOld != null) {
                ipWhitelistOld.setStartaddress(ipWhitelist.getStartaddress());
                ipWhitelistOld.setEndaddress(ipWhitelist.getEndaddress());
                ipWhitelistOld.setRemark(ipWhitelist.getRemark());
                ipWhitelistMapper.updateByPrimaryKeySelective(ipWhitelistOld);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除IP白名单
     * @param ipWhiteListID ip地址ID
     * @return 更新结果
     */
    public boolean deleteWhiteList(Integer ipWhiteListID) {
        if (ipWhiteListID != null) {
            IPWhitelist ipWhitelist = ipWhitelistMapper.selectByPrimaryKey(ipWhiteListID);
            if (ipWhitelist != null) {
                ipWhitelistMapper.deleteByPrimaryKey(ipWhiteListID);
                return true;
            }
        }
        return false;
    }
}
