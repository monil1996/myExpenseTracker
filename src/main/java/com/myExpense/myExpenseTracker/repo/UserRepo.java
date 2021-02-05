package com.myExpense.myExpenseTracker.repo;

import com.myExpense.myExpenseTracker.entity.User;
import com.myExpense.myExpenseTracker.exceptions.EtAuthException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    User findByEmail(String email);
    User save(User user);
    void delete(User user);
}
