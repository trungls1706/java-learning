package com.example.learning.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUser() {
        return "hello from service";
    }
}
