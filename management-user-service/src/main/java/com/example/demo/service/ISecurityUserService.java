package com.example.demo.service;

public interface ISecurityUserService {
    String validatePasswordResetToken(long id, String token);
}
