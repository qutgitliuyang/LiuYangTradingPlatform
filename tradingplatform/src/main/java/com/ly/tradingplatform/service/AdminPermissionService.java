package com.ly.tradingplatform.service;

import com.ly.tradingplatform.dao.AdminPermissionDAO;
import com.ly.tradingplatform.dao.AdminRolePermissionDAO;
import com.ly.tradingplatform.pojo.AdminPermission;
import com.ly.tradingplatform.pojo.AdminRole;
import com.ly.tradingplatform.pojo.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminPermissionService {
    @Autowired
    AdminPermissionDAO adminPermissionDAO;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;
    @Autowired
    UserService userService;

    public List<AdminPermission> list() {return adminPermissionDAO.findAll();}

    public Set<String> listPermissionURLsByUser(String username) {
        List<Integer> rids = adminRoleService.listRolesByUser(username)
                .stream().map(AdminRole::getId).collect(Collectors.toList());

        List<Integer> pids = adminRolePermissionDAO.findAllByRidIn(rids)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());

        List<AdminPermission> perms = adminPermissionDAO.findAllById(pids);

        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());

        return URLs;
    }

    public AdminPermission findById(int id) {
        return adminPermissionDAO.findById(id);
    }

    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = adminPermissionDAO.findAll();
        for (AdminPermission p: ps) {
            if (p.getUrl().equals(requestAPI)) {
                return true;
            }
        }
        return false;
    }

    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(rid);
        List<AdminPermission> perms = new ArrayList<>();
        for (AdminRolePermission rp : rps) {
            perms.add(adminPermissionDAO.findById(rp.getPid()));
        }
        return perms;
    }
}
