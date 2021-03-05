package com.webshop.simplewebapplication.Service;

import com.webshop.simplewebapplication.database.Item.ItemDAOHib;
import com.webshop.simplewebapplication.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDAOHib dataBase = new ItemDAOHib();

    public boolean addItem(Item item) {
        dataBase.addItem(item);
        return true;
    }

    public void addItemToCart(int id) {
        dataBase.addItemToCart(id);
    }

    public List<Item> findAll() {
        return dataBase.findAll();
    }

    public Item findById(int id) {
        return dataBase.findById(id);
    }

    public int countOfItems(){
        return dataBase.countOfItems();
    }

    public void deleteFromCart(CartItem cartItem){
        dataBase.deleteFromCart(cartItem);
    }

    public boolean deleteItem(int id) {
        dataBase.deleteItem(id);
        return true;
    }

    public List<Item> findAllInCart(Cart cart){
        return dataBase.findAllInCart(cart);
    }

    public int getSumInCart(Cart cart) {
        return dataBase.getSumInCart(cart);
    }

    public List<Item> getAllByCategory(Category category) {
        return dataBase.getAllByCategory(category);
    }

    public List<Item> searchByWord(String name) {
        return dataBase.searchByWord(name);
    }

    public List<Item> searchByUser(MyUser user) {
        return dataBase.searchByUser(user);
    }

    public List<CartItem> findAllCartItems(Cart cart) {
        return dataBase.findAllCartItems(cart);
    }
}
