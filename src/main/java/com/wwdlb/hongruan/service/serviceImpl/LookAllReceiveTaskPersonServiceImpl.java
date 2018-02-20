package com.wwdlb.hongruan.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 管理所有接包个人服务实例
 */
@Service
public class LookAllReceiveTaskPersonServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * pageHelper分页查询
     * @param page 当前页（从第一页开始）
     * @param limit 每页显示数量
     * @return 包含目标信息及辅助信息
     */
    public PageInfo<ReceiveTask_Personal> findAllReceiveTaskPersonByPage(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<ReceiveTask_Personal> receiveTaskPersonalList = receiveTask_personalMapper.selectAll();
        return new PageInfo<>(receiveTaskPersonalList);
    }
}
