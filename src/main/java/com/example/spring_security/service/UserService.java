package com.example.spring_security.service;

import com.example.spring_security.entity.User;
import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User show(int id);

    void update(User updatedUser);

    void delete(int id);

    User findUsersByEmail(String username);

    void updateUser(User updateduser, int id);
}
