package com.webshop.simplewebapplication.database.Item;

import com.webshop.simplewebapplication.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemDAO {
    void addItem(Item item);
    List<Item> findAll();
    Item findById(int id);
    void deleteItem(int id);
    int countOfItems();
    List<Item> findAllInCart(Cart cart);
    void addItemToCart(int id);
    void deleteFromCart(CartItem cartItem);
    int getSumInCart(Cart cart);
    List<Item> getAllByCategory(Category category);
    List<Item> searchByWord(String name);
    List<Item> searchByUser(MyUser user);
    List<CartItem> findAllCartItems(Cart cart);
}
