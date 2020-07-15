package com.example.demo.payload;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }


    public AuthResponse( User user) {

        this.user = user;
    }



}
