package com.rymcu.horizon.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rymcu.horizon.core.result.GlobalResult;
import com.rymcu.horizon.core.result.GlobalResultGenerator;
import com.rymcu.horizon.enumerate.DelFlag;
import com.rymcu.horizon.model.BindUserRoleInfo;
import com.rymcu.horizon.model.UserInfo;
import com.rymcu.horizon.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2024/8/10 17:30.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.web
 */
@RestController
@RequestMapping("/api/v1/admin/user")
@PreAuthorize("hasRole('admin')")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/detail/{idUser}")
    public GlobalResult<UserInfo> user(@PathVariable Long idUser) {
        return GlobalResultGenerator.genSuccessResult(userService.findUserInfoById(idUser));
    }

    @PostMapping("/post")
    public GlobalResult<Boolean> addUser(@RequestBody UserInfo userInfo) {
        return GlobalResultGenerator.genSuccessResult(userService.saveUser(userInfo));
    }

    @PutMapping("/post")
    public GlobalResult<Boolean> updateUser(@RequestBody UserInfo userInfo) {
        return GlobalResultGenerator.genSuccessResult(userService.saveUser(userInfo));
    }

    @PostMapping("/update-status")
    public GlobalResult<Boolean> updateUserStatus(@RequestBody UserInfo userInfo) {
        return GlobalResultGenerator.genSuccessResult(userService.updateStatus(userInfo.getIdUser(), userInfo.getStatus()));
    }

    @PostMapping("/reset-password")
    public GlobalResult<ObjectNode> resetPassword(@RequestBody UserInfo userInfo) {
        String password = userService.resetPassword(userInfo.getIdUser());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put("password", password);
        return GlobalResultGenerator.genSuccessResult(jsonObject);
    }

    @PostMapping("/bind-role")
    public GlobalResult<Boolean> bindUserRole(@RequestBody BindUserRoleInfo bindUserRoleInfo) {
        return GlobalResultGenerator.genSuccessResult(userService.bindUserRole(bindUserRoleInfo));
    }

    @DeleteMapping("/update-del-flag")
    public GlobalResult<Boolean> updateDelFlag(Long idUser) {
        return GlobalResultGenerator.genSuccessResult(userService.updateDelFlag(idUser, DelFlag.DELETE.ordinal()));
    }
}
