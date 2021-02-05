package com.myExpense.myExpenseTracker.controller;

import com.myExpense.myExpenseTracker.entity.User;
import com.myExpense.myExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Map<String, String> userMap) {
        String email = userMap.get("email");
        String password = userMap.get("password");
        User user = userService.findUserByEmailAndPass(email, password);
        return new ResponseEntity<>(userService.generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody Map<String, String> userMap) {
        String firstName = userMap.get("firstName");
        String lastName = userMap.get("lastName");
        String email = userMap.get("email");
        String password = userMap.get("password");
        User user = userService.signUp(firstName, lastName, email, password);
        return new ResponseEntity<>("User Registered Successfully!", HttpStatus.OK);
    }

}
