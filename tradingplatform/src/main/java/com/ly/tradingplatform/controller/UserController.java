package com.ly.tradingplatform.controller;

import com.ly.tradingplatform.pojo.User;
import com.ly.tradingplatform.result.Result;
import com.ly.tradingplatform.result.ResultFactory;
import com.ly.tradingplatform.service.AdminRoleService;
import com.ly.tradingplatform.service.AdminUserRoleService;
import com.ly.tradingplatform.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @CrossOrigin
    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }

    @GetMapping("/api/user/{id}")
    @CrossOrigin
    public Result list(@PathVariable("id") int id) throws Exception {
        return ResultFactory.buildSuccessResult(userService.findById(id));
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    @PutMapping("/api/user/edit")
    public Result editPersonalUser(@RequestBody User requestUser) {
        userService.editPersonalUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    @PostMapping("/api/admin/user/delete")
    public Result delete(@RequestBody User requestUser){
        userService.deleteById(requestUser.getId());
        return ResultFactory.buildSuccessResult("删除用户成功");
    }

    @GetMapping("/api/getCurrentUser")
    public Result getCurrentUser() {
        return ResultFactory.buildSuccessResult(userService.getCurrentUser());
    }
}
