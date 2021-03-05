package com.webshop.simplewebapplication.database.Category;

import com.webshop.simplewebapplication.database.HibernateSessionFactoryUtil;
import com.webshop.simplewebapplication.model.Category;
import com.webshop.simplewebapplication.model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDAOHib implements CategoryDAO{
    @Override
    public Category getCategoryByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Category where name = :name");
            query.setParameter("name", name);
            return (Category) query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Category> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            List<Category> categories = (List<Category>) session.createQuery("from Category").list();
            return categories;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Category getCategoryById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Category where id = :id");
            query.setParameter("id", id);
            return (Category) query.list().get(0);
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }
}
