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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ListController {

    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartService cartService;
    @Autowired
    CustomUserDetailsService userService;

    static final Logger logger = LoggerFactory.getLogger(ListController.class);

    @GetMapping("/")
    public ModelAndView index() {
        List<Item> items = itemService.findAll();
        List<Category> categories = categoryService.findAll();
        logger.info("Got all items");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("items", items);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView cart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Cart cart = cartService.findCartByName(currentUserName);

        List<Item> items = itemService.findAllInCart(cart);

        int sumInCart = itemService.getSumInCart(cart);
        logger.info("Got all items in cart");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        modelAndView.addObject("items", items);
        modelAndView.addObject("sum",sumInCart);
        return modelAndView;
    }

    @RequestMapping(path = "/item/{id}/show", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int id) {
        Item item = itemService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("details");
        modelAndView.addObject("item", item);
        logger.info("Showed details item with item_id: " + id);
        return modelAndView;
    }

    @RequestMapping(path = "/category/{id}/show", method = RequestMethod.GET)
    public ModelAndView showCategory(@PathVariable("id") int id) {
        Category category = categoryService.getCategoryById(id);
        List<Item> items = itemService.getAllByCategory(category);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("items", items);
        modelAndView.addObject("return", true);
        modelAndView.addObject("cat", category.getName());
        logger.info("Showed details item with category_id: " + id);
        return modelAndView;
    }

    @RequestMapping(path = "/item/{name}/find", method = RequestMethod.GET)
    public ModelAndView showCategory(@PathVariable("name") String name) {
        List<Item> items = itemService.searchByWord(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("items", items);
        modelAndView.addObject("return", true);
        modelAndView.addObject("cat", "Search for: " + name);
        logger.info("Found items with name like: " + name);
        return modelAndView;
    }

    @RequestMapping(path = "/showuseritems", method = RequestMethod.GET)
    public ModelAndView showUserItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        MyUser user = userService.findByLogin(currentUserName);

        List<Item> items = itemService.searchByUser(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");
        modelAndView.addObject("items", items);
        modelAndView.addObject("showItems", true);
        logger.info("Found items for user: " + currentUserName);
        return modelAndView;
    }

}
