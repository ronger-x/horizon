package com.rymcu.horizon.web;

import com.rymcu.horizon.core.result.GlobalResult;
import com.rymcu.horizon.core.result.GlobalResultGenerator;
import com.rymcu.horizon.entity.Role;
import com.rymcu.horizon.enumerate.DelFlag;
import com.rymcu.horizon.model.BindRoleMenuInfo;
import com.rymcu.horizon.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created on 2024/8/10 17:27.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.web
 */
@RestController
@RequestMapping("/api/v1/admin/role")
@PreAuthorize("hasRole('admin')")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/detail/{idRole}")
    public GlobalResult<Role> role(@PathVariable Long idRole) {
        return GlobalResultGenerator.genSuccessResult(roleService.findById(idRole));
    }

    @PostMapping("/post")
    public GlobalResult<Boolean> addRole(@RequestBody Role role) {
        return GlobalResultGenerator.genSuccessResult(roleService.saveRole(role));
    }

    @PutMapping("/post")
    public GlobalResult<Boolean> updateRole(@RequestBody Role role) {
        return GlobalResultGenerator.genSuccessResult(roleService.saveRole(role));
    }

    @PostMapping("/update-status")
    public GlobalResult<Boolean> updateRoleStatus(@RequestBody Role role) {
        return GlobalResultGenerator.genSuccessResult(roleService.updateStatus(role.getIdRole(), role.getStatus()));
    }

    @GetMapping("/{idRole}/menus")
    public GlobalResult<Set<Long>> menus(@PathVariable Long idRole) {
        return GlobalResultGenerator.genSuccessResult(roleService.findRoleMenus(idRole));
    }

    @PostMapping("/bind-menu")
    public GlobalResult<Boolean> bindRoleMenu(@RequestBody BindRoleMenuInfo bindRoleMenuInfo) {
        return GlobalResultGenerator.genSuccessResult(roleService.bindRoleMenu(bindRoleMenuInfo));
    }

    @DeleteMapping("/update-del-flag")
    public GlobalResult<Boolean> updateDelFlag(Long idRole) {
        return GlobalResultGenerator.genSuccessResult(roleService.updateDelFlag(idRole, DelFlag.DELETE.ordinal()));
    }
}
