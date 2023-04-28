package com.flaneur.blog.service;

import com.flaneur.blog.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
