package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 接包人搜索小任务服务实例
 */
@Service
public class SearchBySmallTaskNameServiceImpl {
   @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 按小任务名称查找
     * @param smallTaskName 小任务名称
     * @return 小任务列表
     */
   public ArrayList<SmallTask> searchBySmallTaskName(String smallTaskName) {
       if (smallTaskName == null || smallTaskName.equals("")) {
           return null;
       }
       String searchName = "%" + smallTaskName + "%";
       return smallTaskMapper.searchBySmallTaskName(searchName);
   }
}
