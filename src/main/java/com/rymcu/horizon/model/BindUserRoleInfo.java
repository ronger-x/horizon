package com.rymcu.horizon.model;

import lombok.Data;

import java.util.Set;

/**
 * Created on 2024/5/5 10:52.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.model
 */
@Data
public class BindUserRoleInfo {

    private Long idUser;

    private Set<Long> idRoles;

}
