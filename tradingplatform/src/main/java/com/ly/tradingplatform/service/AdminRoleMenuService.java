package com.ly.tradingplatform.service;

import com.ly.tradingplatform.dao.AdminRoleMenuDAO;
import com.ly.tradingplatform.pojo.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminRoleMenuService {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDAO.findAllByRid(rid);
    }

    public List<AdminRoleMenu> findAllByRidIn(List<Integer> rids) {
        return adminRoleMenuDAO.findAllByRidIn(rids);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDAO.save(rm);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int rid, Map menusIds) {
        adminRoleMenuDAO.deleteAllByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();
        for (Integer mid : (List<Integer>)menusIds.get("menusIds")) {
            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setMid(mid);
            rm.setRid(rid);
            rms.add(rm);
        }
        adminRoleMenuDAO.saveAll(rms);
    }

    @Transactional
    public void deleteRoleMenu(int rid) {
        adminRoleMenuDAO.deleteAllByRid(rid);
    }

}
