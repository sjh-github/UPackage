package com.wwdlb.hongruan.web.personaladministrator;

import com.github.pagehelper.PageInfo;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal.LookAllReceiveTaskPersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LookAllPersonController {

    @Autowired
    private LookAllReceiveTaskPersonServiceImpl lookAllReceiveTaskPersonServiceImpl;

    /**
     * pageHelper分页查询
     * @param page 当前页（从第一页开始）
     * @param per_page 每页显示数量
     * @return 包含目标信息及辅助信息
     */
    @GetMapping("/web/receiveTaskPersonal")
    public PageInfo<ReceiveTask_Personal> findAllByPage(@RequestParam int page, @RequestParam int per_page) {
        return lookAllReceiveTaskPersonServiceImpl.findAllReceiveTaskPersonByPage(page, per_page);
    }
}
