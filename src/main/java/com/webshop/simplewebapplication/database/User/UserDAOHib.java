package com.webshop.simplewebapplication.database.User;

import com.webshop.simplewebapplication.database.HibernateSessionFactoryUtil;
import com.webshop.simplewebapplication.database.Item.ItemDAO;
import com.webshop.simplewebapplication.model.Cart;
import com.webshop.simplewebapplication.model.Category;
import com.webshop.simplewebapplication.model.MyUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserDAOHib implements UserDAO {

    @Override
    public MyUser findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM MyUser where login = :login");
            query.setParameter("login", login);
            return (MyUser) query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void createUser(MyUser user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(MyUser user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
