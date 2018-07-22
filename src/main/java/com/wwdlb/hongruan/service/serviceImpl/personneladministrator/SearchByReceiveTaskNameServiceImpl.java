package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 按接包人员姓名搜索服务实例
 */
@Service
public class SearchByReceiveTaskNameServiceImpl {
   @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 搜索未审核接包人姓名
     * @param name 接包人姓名
     * @return 接包人列表
     */
   public ArrayList<ReceiveTask_Personal> searchNotPassReceivePerson(String name) {
       if (name == null || name.equals("")) {
           return null;
       }
       String searchName = "%" + name + "%";
       return receiveTask_personalMapper.searchByNameAndHaveChecked("F", searchName);
   }

    /**
     * 搜索已审核接包人姓名
     * @param name 接包人姓名
     * @return 接包人列表
     */
    public ArrayList<ReceiveTask_Personal> searchhavePassReceivePerson(String name) {
        if (name == null || name.equals("")) {
            return null;
        }
        String searchName = "%" + name + "%";
        return receiveTask_personalMapper.searchByNameAndHaveChecked("T", searchName);
    }
}
