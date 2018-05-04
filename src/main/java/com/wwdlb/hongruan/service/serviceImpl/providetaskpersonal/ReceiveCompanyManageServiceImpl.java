package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.ReceiveTask_CompanyMapper;
import com.wwdlb.hongruan.model.ReceiveTask_Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接包公司管理服务实例
 */
@Service
public class ReceiveCompanyManageServiceImpl {
    @Autowired
    private ReceiveTask_CompanyMapper receiveTask_companyMapper;

    /**
     * 获取接包公司
     * @return 接包公司
     */
   /* public ReceiveTask_Company getOneReceiveTaskCompany() {
        return receiveTask_companyMapper.selectByPrimaryKey("wangyi@163.com");
    }*/
}
