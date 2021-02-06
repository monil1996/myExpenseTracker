package com.myExpense.myExpenseTracker.service;

import com.myExpense.myExpenseTracker.entity.Category;
import com.myExpense.myExpenseTracker.exceptions.EtResourceNotFoundException;
import com.myExpense.myExpenseTracker.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


    public List<Category> getAllCategories(String userId) throws EtResourceNotFoundException {

        List<Category> res =  categoryRepo.findByUserId(userId);

        if(res.size() == 0 || res == null){
            throw  new EtResourceNotFoundException("No category found");
        }

        return res;
    }

    public void addCategory(String userId, String title, String description) {
        Category category = new Category();
        category.setUserId(userId);
        category.setDescription(description);
        category.setTitle(title);

        categoryRepo.save(category);
    }

    public Category getCategoryById(String userId, String categoryId) {
        Category res = categoryRepo.findByUserIdAndId(userId,categoryId);

        if(res == null){
            throw  new EtResourceNotFoundException("No category found");
        }

        return res;
    }
}
