package com.rymcu.horizon.web;

import com.rymcu.horizon.core.exception.AccountExistsException;
import com.rymcu.horizon.core.exception.ServiceException;
import com.rymcu.horizon.core.result.GlobalResult;
import com.rymcu.horizon.core.result.GlobalResultGenerator;
import com.rymcu.horizon.core.result.GlobalResultMessage;
import com.rymcu.horizon.entity.User;
import com.rymcu.horizon.model.ForgetPasswordInfo;
import com.rymcu.horizon.model.RegisterInfo;
import com.rymcu.horizon.service.JavaMailService;
import com.rymcu.horizon.service.UserService;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

/**
 * Created on 2024/4/18 18:43.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.web
 */
@RestController
@RequestMapping("/api/v1/common")
public class CommonController {

    @Resource
    private JavaMailService javaMailService;
    @Resource
    private UserService userService;

    @GetMapping("/get-email-code")
    public GlobalResult<String> getEmailCode(@RequestParam("email") String email) throws MessagingException, AccountExistsException {
        User user = userService.findByAccount(email);
        if (user != null) {
            throw new AccountExistsException("该邮箱已被注册!");
        } else {
            int result = javaMailService.sendEmailCode(email);
            if (result == 0) {
               return GlobalResultGenerator.genErrorResult(GlobalResultMessage.SEND_FAIL.getMessage());
            }
        }
        return GlobalResultGenerator.genSuccessResult(GlobalResultMessage.SEND_SUCCESS.getMessage());
    }

    @GetMapping("/get-forget-password-email")
    public GlobalResult<String> getForgetPasswordEmail(@RequestParam("email") String email) throws MessagingException, ServiceException, AccountNotFoundException {
        User user = userService.findByAccount(email);
        if (user != null) {
            int result = javaMailService.sendForgetPasswordEmail(email);
            if (result == 0) {
                throw new ServiceException(GlobalResultMessage.SEND_FAIL.getMessage());
            }
        } else {
            throw new AccountNotFoundException("未知账号");
        }
        return GlobalResultGenerator.genSuccessResult(GlobalResultMessage.SEND_SUCCESS.getMessage());
    }

    @PatchMapping("/forget-password")
    public GlobalResult<Boolean> forgetPassword(@RequestBody ForgetPasswordInfo forgetPassword) throws ServiceException {
        boolean flag = userService.forgetPassword(forgetPassword.getCode(), forgetPassword.getPassword());
        return GlobalResultGenerator.genSuccessResult(flag);
    }

    @PostMapping("/register")
    public GlobalResult<Boolean> register(@RequestBody RegisterInfo registerInfo) throws AccountExistsException {
        boolean flag = userService.register(registerInfo.getEmail(), registerInfo.getNickname(), registerInfo.getPassword(), registerInfo.getCode());
        return GlobalResultGenerator.genSuccessResult(flag);
    }
}
