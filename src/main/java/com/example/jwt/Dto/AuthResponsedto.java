package com.example.jwt.Dto;

import lombok.Data;

@Data
public class AuthResponsedto {
    private String accesstoken;

    private String tokentype="Bearer";
    public AuthResponsedto(String accesstoken){
        this.accesstoken=accesstoken;
    }
}
