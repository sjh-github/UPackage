package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.sun.org.apache.regexp.internal.RE;
import com.wwdlb.hongruan.mapper.IPWhitelistMapper;
import com.wwdlb.hongruan.model.IPWhitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if (!isLegalIP(ipWhitelist.getStartaddress(), ipWhitelist.getEndaddress())) {
                return false;
            }
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

    public boolean insertIPWhiltList(String startAddress, String endAddress, String remark) {
        if (!isLegalIP(startAddress, endAddress)) {
            return false;
        }
        IPWhitelist ipWhitelist = new IPWhitelist();
        ipWhitelist.setStartaddress(startAddress);
        ipWhitelist.setEndaddress(endAddress);
        ipWhitelist.setRemark(remark);
        ipWhitelistMapper.insert(ipWhitelist);
        return true;
    }

    private boolean isLegalIP(String startAddress, String endAddress) {
        if (startAddress == null || endAddress == null) {
            return false;
        }
        String ipCheckStr = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
        Pattern pattern = Pattern.compile(ipCheckStr);
        Matcher startMatcher = pattern.matcher(startAddress);
        Matcher endMatcher = pattern.matcher(endAddress);
        if (startMatcher.find() && endMatcher.find()) {
            if (endAddress.compareTo(startAddress) >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
