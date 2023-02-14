package com.moon.quotes_rest_api.controller.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private String email;
    private String accessToken;
}
