package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.PersonAndSmallTaskMapper;
import com.wwdlb.hongruan.model.PersonAndSmallTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetReceiveSmallTaskPersonNameBySmallTaskIDServiceImpl {
   @Autowired
    private PersonAndSmallTaskMapper personAndSmallTaskMapper;

    /**
     * 根据小任务ID获取接包人邮箱
     * @param smallTaskID 小任务ID
     * @return 接包人邮箱/NULL
     */
   public String getReceiveSmallTaskPersonNameBySmallTaskID(Integer smallTaskID) {
       PersonAndSmallTask personAndSmallTask = personAndSmallTaskMapper.selectBySmallTaskID(smallTaskID);
       if (personAndSmallTask != null) {
           return personAndSmallTask.getEmail();
       }
       return null;
   }
}
