package com.rymcu.horizon.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rymcu.horizon.entity.Role;
import com.rymcu.horizon.model.BindRoleMenuInfo;
import com.rymcu.horizon.model.RoleSearch;

import java.util.List;
import java.util.Set;

/**
 * Created on 2024/4/13 22:06.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.service
 */
public interface RoleService {
    List<Role> findRolesByIdUser(Long idUser);

    Boolean saveRole(Role role);

    List<Role> findRoles(Page<Role> page, RoleSearch search);

    Boolean bindRoleMenu(BindRoleMenuInfo bindRoleMenuInfo);

    Boolean updateStatus(Long idRole, Integer status);

    Set<Long> findRoleMenus(Long idRole);

    Boolean updateDelFlag(Long idRole, Integer delFlag);

    Role findById(Long idRole);
}
