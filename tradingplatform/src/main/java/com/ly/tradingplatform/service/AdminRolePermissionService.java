package com.ly.tradingplatform.service;

import com.ly.tradingplatform.dao.AdminRolePermissionDAO;
import com.ly.tradingplatform.pojo.AdminPermission;
import com.ly.tradingplatform.pojo.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDAO.findAllByRid(rid);
    }

    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDAO.deleteAllByRid(rid);
        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDAO.saveAll(rps);
    }

    @Transactional
    public void deleteRolePerm(int rid) {
        adminRolePermissionDAO.deleteAllByRid(rid);
    }
}
