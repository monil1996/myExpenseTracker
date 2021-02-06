package com.myExpense.myExpenseTracker.controller;

import com.myExpense.myExpenseTracker.entity.Category;
import com.myExpense.myExpenseTracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        List<Category> categories = categoryService.getAllCategories(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity addCategory(HttpServletRequest request, @RequestBody Map<String, Object> categoryMap) {
        String userId = (String) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>("Category Saved Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest request, @PathVariable("categoryId") String categoryId) {
        String userId = (String) request.getAttribute("userId");
        Category category = categoryService.getCategoryById(userId, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
