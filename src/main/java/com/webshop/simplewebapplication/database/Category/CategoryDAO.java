package com.webshop.simplewebapplication.database.Category;

import com.webshop.simplewebapplication.model.Category;

import java.util.List;

public interface CategoryDAO {
    public Category getCategoryByName(String name);
    public List<Category> findAll();

    Category getCategoryById(int id);
}
