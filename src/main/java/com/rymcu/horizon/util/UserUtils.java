package com.rymcu.horizon.util;

import com.rymcu.horizon.auth.JwtConstants;
import com.rymcu.horizon.auth.JwtUtils;
import com.rymcu.horizon.auth.TokenModel;
import com.rymcu.horizon.core.result.ResultCode;
import com.rymcu.horizon.entity.User;
import com.rymcu.horizon.mapper.UserMapper;
import com.rymcu.horizon.model.TokenUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * Created on 2024/4/13 15:28.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.util
 */
public class UserUtils {

    private static final UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);

    /**
     * 通过token获取当前用户的信息
     * @return User
     */
    public static User getCurrentUserByToken() {
        String authHeader = ContextHolderUtils.getRequest().getHeader(JwtConstants.AUTHORIZATION);
        TokenModel tokenModel = JwtUtils.getTokenModel(authHeader);
        User user = userMapper.selectByAccount(tokenModel.getUsername());
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException(ResultCode.UNKNOWN_ACCOUNT.getMessage());
    }

    public static TokenUser getTokenUser(String token) {
        TokenModel tokenModel = JwtUtils.getTokenModel(token);
        User user = userMapper.selectByAccount(tokenModel.getUsername());
        if (Objects.nonNull(user)) {
            TokenUser tokenUser = new TokenUser();
            BeanCopierUtil.copy(user, tokenUser);
            tokenUser.setAccount(user.getEmail());
            tokenUser.setToken(token);
            return tokenUser;
        }
        throw new UsernameNotFoundException(ResultCode.UNKNOWN_ACCOUNT.getMessage());
    }
}
