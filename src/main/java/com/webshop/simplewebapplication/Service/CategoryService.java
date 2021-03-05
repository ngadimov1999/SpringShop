package com.webshop.simplewebapplication.Service;

import com.webshop.simplewebapplication.database.Category.CategoryDAOHib;
import com.webshop.simplewebapplication.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAOHib dataBase = new CategoryDAOHib();

    public Category getCategoryByName(String name){
        return dataBase.getCategoryByName(name);
    }

    public List<Category> findAll(){
        return dataBase.findAll();
    }

    public Category getCategoryById(int id) {
        return dataBase.getCategoryById(id);
    }
}
