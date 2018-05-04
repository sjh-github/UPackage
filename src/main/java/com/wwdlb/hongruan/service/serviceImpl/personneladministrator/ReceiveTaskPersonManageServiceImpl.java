package com.wwdlb.hongruan.service.serviceImpl.personneladministrator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 接包人管理
 */
@Service
public class ReceiveTaskPersonManageServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * 未核对人员
     * @return 未核对人员名单
     */
    public PageInfo<ReceiveTask_Personal> noCheckReceiveTaskPerson(int page, int per_page) {
        PageHelper.startPage(page, per_page);
        return new PageInfo<>(receiveTask_personalMapper.selectByHaveChecked("F"));
    }

    /**
     * 已核对人员
     * @return 已核对人员名单
     */
    public PageInfo<ReceiveTask_Personal> yesCheckReceiveTaskPerson(int page, int per_page) {
        PageHelper.startPage(page, per_page);
        return new PageInfo<>(receiveTask_personalMapper.selectByHaveChecked("T"));
    }
}
