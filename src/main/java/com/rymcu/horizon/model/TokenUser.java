package com.rymcu.horizon.model;


import lombok.Data;

/**
 * @author ronger
 */
@Data
public class TokenUser {

    private Long idUser;

    private String account;

    private String token;

    private String refreshToken;

}
