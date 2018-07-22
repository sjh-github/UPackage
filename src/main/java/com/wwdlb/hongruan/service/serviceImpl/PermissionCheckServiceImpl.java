package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.PermissionAndPersonAndTaskMapper;
import com.wwdlb.hongruan.model.PermissionAndPersonAndTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 权限验证
 */
@Service
public class PermissionCheckServiceImpl {
    @Autowired
    private PermissionAndPersonAndTaskMapper permissionAndPersonAndTaskMapper;

    /**
     * 检测是否含有权限
     * @param email 验证账号
     * @param smallTaskID 小任务ID
     * @param permissionID 权限ID
     * @return true/false;
     */
    public boolean havePermission(String email, Integer smallTaskID, Integer permissionID) {
        if (email == null || smallTaskID == null || permissionID == null) {
            return true;
        }
        ArrayList<PermissionAndPersonAndTask> permissionAndPersonAndTasks = permissionAndPersonAndTaskMapper.selectByEmail(email);
        if (permissionAndPersonAndTasks == null) {
            return false;
        }
        for (PermissionAndPersonAndTask permissionAndPersonAndTask : permissionAndPersonAndTasks) {
            if (permissionAndPersonAndTask.getTaskid() == smallTaskID && permissionAndPersonAndTask.getPermissionid() == permissionID) {
                return true;
            }
        }
        return false;
    }
}
