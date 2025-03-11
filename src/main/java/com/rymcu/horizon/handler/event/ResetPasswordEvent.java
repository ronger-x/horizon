package com.rymcu.horizon.handler.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created on 2024/7/17 20:07.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.handler.event
 */
@Data
@AllArgsConstructor
public class ResetPasswordEvent {

    private String email;

    private String code;
}
