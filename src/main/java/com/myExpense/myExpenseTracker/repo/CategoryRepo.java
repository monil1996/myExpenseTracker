package com.myExpense.myExpenseTracker.repo;

import com.myExpense.myExpenseTracker.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends MongoRepository<Category, String> {

    List<Category> findByUserId(String userId);
    Category findByUserIdAndId(String userId, String categoryId);
}
