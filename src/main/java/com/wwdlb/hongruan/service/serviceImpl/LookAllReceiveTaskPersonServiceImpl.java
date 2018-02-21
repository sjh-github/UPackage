package com.wwdlb.hongruan.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 管理所有接包个人服务实例
 */
@Service
public class LookAllReceiveTaskPersonServiceImpl {

    private Logger logger = LoggerFactory.getLogger(String.valueOf(LookAllReceiveTaskPersonServiceImpl.this));

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    /**
     * pageHelper分页查询
     * @param page 当前页（从第一页开始）
     * @param per_page 每页显示数量
     * @return 包含目标信息及辅助信息
     */
    public PageInfo<ReceiveTask_Personal> findAllReceiveTaskPersonByPage(int page, int per_page) {
        if (page <= 0) {
            logger.error("page should be bigger than 0");
            throw new RuntimeException("page should be bigger than 0");
        }
        if (per_page <= 0) {
            logger.error("per_page should be bigger than 0");
            throw new RuntimeException("per_page should be bigger than 0");
        }
        PageHelper.startPage(page, per_page);
        List<ReceiveTask_Personal> receiveTaskPersonalList = receiveTask_personalMapper.selectAll();
        return new PageInfo<>(receiveTaskPersonalList);
    }
}
