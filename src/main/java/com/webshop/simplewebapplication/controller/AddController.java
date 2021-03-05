package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.CartService;
import com.webshop.simplewebapplication.Service.CategoryService;
import com.webshop.simplewebapplication.Service.CustomUserDetailsService;
import com.webshop.simplewebapplication.Service.ItemService;
import com.webshop.simplewebapplication.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AddController {

    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CustomUserDetailsService userService;
    @Autowired
    CartService cartService;

    static final Logger logger = LoggerFactory.getLogger(AddController.class);

    @PostMapping("/add")
    public ModelAndView add(@RequestParam("name") String name,
                            @RequestParam("price") int price,
                            @RequestParam("phone") String phone,
                            @RequestParam("category") String category)  {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Category cat = categoryService.getCategoryByName(category);
        MyUser user = userService.findByLogin(currentUserName);

        itemService.addItem(new Item(0, name, price, phone, cat, user));

        logger.info("Added item with name: " + name + ", price " + price + ", category " + category + " by user: " + currentUserName);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addItem");

        List<Category> categories = categoryService.findAll();

        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(path = "/add/{id}/cart", method = RequestMethod.POST)
    public void addInCart(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Item item = itemService.findById(id);
        Cart cart = cartService.findCartByName(currentUserName);

        cartService.addItemToCart(new CartItem(0, item, cart));
        logger.info("Added item to cart with item_id: " + id);
    }
}
