package com.webshop.simplewebapplication.database.User;

import com.webshop.simplewebapplication.model.MyUser;

public interface UserDAO {
    MyUser findByLogin(String login);

    void createUser(MyUser user);

    void deleteUser(MyUser user);
}
