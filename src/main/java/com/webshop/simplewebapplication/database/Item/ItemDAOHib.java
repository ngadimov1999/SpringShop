package com.webshop.simplewebapplication.database.Item;

import com.webshop.simplewebapplication.controller.ListController;
import com.webshop.simplewebapplication.database.HibernateSessionFactoryUtil;
import com.webshop.simplewebapplication.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDAOHib implements ItemDAO {

    @Override
    public void addItem(Item item) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Item> items = (List<Item>) session.createQuery("from Item").list();
            return items;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Item findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Item where id = :id");
            query.setParameter("id", id);
            return (Item)query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteItem(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            Item item = findById(id);
            session.delete(item);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAllInCart(Cart cart) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            List<CartItem> cartItems  = (List<CartItem>) session.createQuery("From CartItem where cart = :cart").setParameter("cart",cart).list();
            ArrayList<Item> items = new ArrayList<>();
            for (int i = 0; i < cartItems.size(); i++){
                items.add(cartItems.get(i).getItem());
            }
            return items;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void addItemToCart(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            Item item = findById(id);
            item.setPhone("Уже в корзине");
            session.update(item);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    @Override
    public void deleteFromCart(CartItem cartItem) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.delete(cartItem);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public int getSumInCart(Cart cart) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            List<CartItem> cartItems  = (List<CartItem>) session.createQuery("From CartItem where cart = :cart").setParameter("cart",cart).list();
            ArrayList<Item> items = new ArrayList<>();
            for (int i = 0; i < cartItems.size(); i++){
                items.add(cartItems.get(i).getItem());
            }
            int sum = 0;
            for (Item item:items) {
                sum += item.getPrice();
            }
            return sum;
        }catch (Exception e){
            return 0;
        }finally {
            session.close();
        }
    }

    public int countOfItems() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Item> items = (List<Item>) session.createQuery("From Item").list();
            return items.size();
        }catch (Exception e){
            return 0;
        }finally {
            session.close();
        }
    }

    public List<Item> getAllByCategory(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Item> items = (List<Item>) session.createQuery("From Item where category = :category").setParameter("category", category).list();
            return items;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Item> searchByWord(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            name = "%" + name + "%";
            List<Item> items = (List<Item>) session.createQuery("From Item where name like :name").setParameter("name", name).list();
            return items;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Item> searchByUser(MyUser user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Item> items = (List<Item>) session.createQuery("From Item where myUser = :user").setParameter("user", user).list();
            return items;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    public List<CartItem> findAllCartItems(Cart cart) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<CartItem> cartItems = (List<CartItem>) session.createQuery("From CartItem where cart = :cart").setParameter("cart", cart).list();
            return cartItems;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }
}
