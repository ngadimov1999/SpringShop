package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.CartService;
import com.webshop.simplewebapplication.Service.CustomUserDetailsService;
import com.webshop.simplewebapplication.model.Cart;
import com.webshop.simplewebapplication.model.MyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView add(@RequestParam("login") String login,
                            @RequestParam("password") String password)  {

        ModelAndView modelAndView = new ModelAndView();
        if (userService.findByLogin(login) == null){
            Cart cart = new Cart(0,login);
            cartService.createCart(cart);
            userService.createUser(new MyUser(0, login, bCryptPasswordEncoder.encode(password), cart));
            logger.info("Created user with login: " + login);
            modelAndView.setViewName("login");
        } else{
            modelAndView.setViewName("registration");
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }
}
