package com.example.demo.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginE {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
