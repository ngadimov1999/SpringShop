package com.webshop.simplewebapplication.Service;

import com.webshop.simplewebapplication.database.Cart.CartDAOHib;
import com.webshop.simplewebapplication.database.Item.ItemDAOHib;
import com.webshop.simplewebapplication.model.Cart;
import com.webshop.simplewebapplication.model.CartItem;
import com.webshop.simplewebapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartDAOHib dataBase = new CartDAOHib();

    public void createCart(Cart cart) {
        dataBase.createCart(cart);
    }

    public Cart findCartByName(String name) {
        return dataBase.findCartByName(name);
    }

    public void addItemToCart(CartItem cartItem) {
        dataBase.addItemToCart(cartItem);
    }

    public CartItem findCartItemByCartAndItem(Cart cart, Item item) {
        return dataBase.findCartItemByCartAndItem(cart, item);
    }

    public void deleteCart(Cart cart) {
        dataBase.deleteCart(cart);
    }
}
