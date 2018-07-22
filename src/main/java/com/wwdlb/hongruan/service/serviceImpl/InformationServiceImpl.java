package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.InformationMapper;
import com.wwdlb.hongruan.model.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class InformationServiceImpl {
    @Autowired
    private InformationMapper informationMapper;

    /**
     * 新增消息通知
     * @param sendName 发件方
     * @param toEmail 收件方
     * @param content 内容
     */
    public void insert(String sendName, String toEmail, String content) {
        Information information = new Information();
        information.setSendname(sendName);
        information.setToemail(toEmail);
        information.setContent(content);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        information.setTime(simpleDateFormat.format(date));
        informationMapper.insert(information);
    }

    /**
     * 删除消息
     * @param informationID
     */
    public void delete(Integer informationID) {
        informationMapper.deleteByPrimaryKey(informationID);
    }

    /**
     * 根据接收方查找所有消息通知
     * @param toEmail 接收方
     * @return 消息列表
     */
    public ArrayList<Information> selectByToEmail(String toEmail) {
        if (toEmail == null) {
            return null;
        } else {
            return informationMapper.selectByToEmail(toEmail);
        }
    }

    public Information selectByPrimaryKey(Integer informationID) {
        return informationMapper.selectByPrimaryKey(informationID);
    }
}
