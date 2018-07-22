package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.PersonnelAdministratorMapper;
import com.wwdlb.hongruan.mapper.ProvideTask_PersonalMapper;
import com.wwdlb.hongruan.mapper.ReceiveTask_PersonalMapper;
import com.wwdlb.hongruan.model.PersonnelAdministrator;
import com.wwdlb.hongruan.model.ProvideTask_Personal;
import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetNameByEmailServiceImpl {
    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private ProvideTask_PersonalMapper provideTask_personalMapper;

    @Autowired
    private PersonnelAdministratorMapper personnelAdministratorMapper;

    public String getReceiveTaskPersonalNameByEmail(String email) {
        if (email != null) {
            ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
            if (receiveTask_personal != null) {
                return receiveTask_personal.getName();
            } else {
                return null;
            }
        }
        return null;
    }

    public String getProvideTaskPersonalNameByEmail(String email) {
        if (email != null) {
            ProvideTask_Personal provideTask_personal = provideTask_personalMapper.selectByPrimaryKey(email);
            if (provideTask_personal != null) {
                return provideTask_personal.getName();
            } else {
                return null;
            }
        }
        return null;
    }

    public String getPersonalAdministratorNameByEmail(String email) {
        if (email != null) {
            PersonnelAdministrator personnelAdministrator = personnelAdministratorMapper.selectByPrimaryKey(email);
            if (personnelAdministrator != null) {
                return personnelAdministrator.getName();
            } else {
                return null;
            }
        }
        return null;
    }
}
