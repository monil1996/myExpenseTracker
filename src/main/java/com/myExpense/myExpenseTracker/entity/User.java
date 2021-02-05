package com.myExpense.myExpenseTracker.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
@Data
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String id;
}
