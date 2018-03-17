package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LookAllSmallTaskByEmailServiceImpl {

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 查看指定接包人对应的小任务列表
     * @param email 接包人邮箱
     * @return 小任务列表/NULL
     */
    /*public ArrayList<SmallTask> lookAllSmallTaskByEmail(String email) {

    }*/
}
