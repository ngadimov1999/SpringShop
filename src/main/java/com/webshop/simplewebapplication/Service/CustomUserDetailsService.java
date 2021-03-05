package com.webshop.simplewebapplication.Service;

import com.webshop.simplewebapplication.database.User.UserDAO;
import com.webshop.simplewebapplication.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser myUser= userDao.findByLogin(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles("USER")
                .build();
        return user;
    }

    public MyUser findByLogin(String currentUserName) {
        return userDao.findByLogin(currentUserName);
    }

    public void createUser(MyUser user) {
        userDao.createUser(user);
    }

    public void deleteUser(MyUser user) {
        userDao.deleteUser(user);
    }
}
