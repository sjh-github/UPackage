package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.*;
import com.wwdlb.hongruan.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登陆服务接口实例
 */
@Service
public class LoginServiceImpl/* implements LoginService*/{

    @Autowired
    private ReceiveTask_PersonalMapper receiveTask_personalMapper;

    @Autowired
    private ProvideTask_PersonalMapper provideTask_personalMapper;

    @Autowired
    private ReceiveTask_CompanyMapper receiveTask_companyMapper;

    @Autowired
    private PersonnelAdministratorMapper personnelAdministratorMapper;

    @Autowired
    private SuperAdministratorMapper superAdministratorMapper;

    final static public String WrongPassword = "WrongPassword";
    final static public String HaveNoAccount = "HaveNoAccount";
    final static public String ReceiveTaskPersonal = "ReceiveTaskPersonal";
    final static public String ProvideTaskPersonal = "ProvideTaskPersonal";
    final static public String ReceiveTaskCompany = "ReceiveTaskCompany";
    final static public String PersonnelAdministrator = "PersonnelAdministrator";
    final static public String SuperAdministrator = "SuperAdministrator";


    /**
     * 接包人员登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    //@Override
    public int loginReceiveTaskPersonal(String email, String password) {
        ReceiveTask_Personal receiveTask_personal = receiveTask_personalMapper.selectByPrimaryKey(email);
        if (receiveTask_personal == null) {
            return -1;
        } else {
            if (!receiveTask_personal.getPassword().equals(password)) {
                return -2;
            } else {
                return 1;
            }
        }
    }

    /**
     * 发包人员登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    //@Override
    public int loginProvideTaskPersonal(String email, String password) {
        ProvideTask_Personal provideTask_personal = provideTask_personalMapper.selectByPrimaryKey(email);
        if (provideTask_personal == null) {
            return -1;
        } else {
            if (provideTask_personal.getPassword().equals(password)) {
                return 1;
            } else {
                return -2;
            }
        }
    }

    /**
     * 接包公司登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    public int loginReceiveTaskCompany(String email, String password) {
        ReceiveTask_Company receiveTask_company = receiveTask_companyMapper.selectByPrimaryKey(email);
        if (receiveTask_company == null) {
            return -1;
        } else {
            if (receiveTask_company.getCompanypassword().equals(password)) {
                return 1;
            } else {
                return -2;
            }
        }
    }

    /**
     * 人事管理员登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    public int loginPersonnelAdministrator(String email, String password) {
        PersonnelAdministrator personnelAdministrator = personnelAdministratorMapper.selectByPrimaryKey(email);
        if (personnelAdministrator == null) {
            return -1;
        } else if (personnelAdministrator.getPassword().equals(password)) {
                return 1;
        } else {
            return -2;
        }
    }

    /**
     * 超级管理员登陆
     * @param email 邮箱
     * @param password 密码
     * @return -1:该邮箱不存在，-2:密码错误，1：登陆成功
     */
    public int loginSuperAdministrator(String email,String password) {
        SuperAdministrator superAdministrator = superAdministratorMapper.selectByPrimaryKey(email);
        if (superAdministrator == null) {
            return -1;
        } else if (superAdministrator.getPassword().equals(password)) {
            return 1;
        } else {
            return -2;
        }
    }

    public String login(String email, String password) {
        //发包人员登陆
        int reslut = loginProvideTaskPersonal(email, password);
        if (reslut == -1) {
            //接包人员登陆
            reslut = loginReceiveTaskPersonal(email, password);
            if (reslut == -1) {
                //接包公司登陆
                reslut = loginReceiveTaskCompany(email, password);
                    if (reslut == -1) {
                        //人事管理员登陆
                        reslut = loginPersonnelAdministrator(email, password);
                        if (reslut == -1) {
                            //超级管理员登陆
                            reslut = loginSuperAdministrator(email, password);
                            if (reslut == -1) {
                                return HaveNoAccount;
                            } else if (reslut == -2) {
                                return WrongPassword;
                            } else {
                                return SuperAdministrator;
                            }
                        } else if (reslut == -2) {
                            return WrongPassword;
                        } else {
                            return PersonnelAdministrator;
                        }
                    } else if (reslut == -2) {
                        return WrongPassword;
                    } else {
                        return ReceiveTaskCompany;
                    }
            } else if (reslut == -2) {
                return WrongPassword;
            } else {
                return ReceiveTaskPersonal;
            }
        } else if (reslut == -2) {
            return WrongPassword;
        } else {
            return ProvideTaskPersonal;
        }
    }
}
