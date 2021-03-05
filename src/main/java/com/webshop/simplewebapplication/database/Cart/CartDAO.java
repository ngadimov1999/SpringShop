package com.webshop.simplewebapplication.database.Cart;

import com.webshop.simplewebapplication.model.Cart;
import com.webshop.simplewebapplication.model.CartItem;
import com.webshop.simplewebapplication.model.Item;

public interface CartDAO {
    public void createCart(Cart cart);
    void addItemToCart(CartItem cartItem);
    Cart findCartByName(String name);
    CartItem findCartItemByCartAndItem(Cart cart, Item item);
    void deleteCart(Cart cart);
}
