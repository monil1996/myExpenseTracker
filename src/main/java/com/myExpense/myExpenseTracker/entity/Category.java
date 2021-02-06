package com.myExpense.myExpenseTracker.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Category")
@Data
public class Category {

    private String id;
    private String userId;
    private String title;
    private String description;
}
