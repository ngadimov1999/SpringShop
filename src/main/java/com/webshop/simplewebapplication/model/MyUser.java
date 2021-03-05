package com.webshop.simplewebapplication.model;

import javax.persistence.*;

@Entity
@Table(name = "my_user")
public class MyUser {

    public MyUser(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    private Cart cart;


    public MyUser(int user_id, String login, String password, Cart cart) {
        this.id = user_id;
        this.login = login;
        this.password = password;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
