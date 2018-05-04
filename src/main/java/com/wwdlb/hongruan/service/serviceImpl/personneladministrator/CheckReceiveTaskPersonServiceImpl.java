package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.InformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审核接包人
 */
@Service
public class CheckReceiveTaskPersonServiceImpl {
   @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

   @Autowired
   private InformationServiceImpl informationServiceImpl;

    /**
     * 审核通过接包人
     * @param email 接包人邮箱
     * @return 审核结果
     */
   public boolean passReceiveTaskPerson(String email) {
       ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
       if (receiveTask_personal != null) {
           receiveTask_personal.setHavechecked("T");
           receiveTask_personalMapper.updateByPrimaryKeySelective(receiveTask_personal);
           informationServiceImpl.insert("系统", email, "您的个人信息已审核成功");
           return true;
       }
       return false;
   }

    /**
     * 审核不通过接包人
     * @param email 接包人邮箱
     * @return 审核结果
     */
    public boolean noPassReceiveTaskPerson(String email) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal != null) {
            receiveTask_personalMapper.deleteByPrimaryKey(email);
            informationServiceImpl.insert("系统", email, "您的个人信息审核失败");
            return true;
        }
        return false;
    }
}
