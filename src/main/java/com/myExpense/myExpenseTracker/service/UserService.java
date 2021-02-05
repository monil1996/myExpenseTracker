package com.myExpense.myExpenseTracker.service;

import com.myExpense.myExpenseTracker.entity.User;
import com.myExpense.myExpenseTracker.exceptions.EtAuthException;
import com.myExpense.myExpenseTracker.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.myExpense.myExpenseTracker.Constants.API_SECRET_KEY;
import static com.myExpense.myExpenseTracker.Constants.TOKEN_VALIDITY;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User findUserByEmailAndPass(String email, String password) throws EtAuthException {

        User user = userRepo.findByEmail(email.toLowerCase());

        if (user == null) {
            throw new EtAuthException("Invalid User Details");
        } else if (user.getPassword() != null) {
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new EtAuthException("Invalid password");
            }
        } else {
            throw new EtAuthException("Invalid email/password");
        }

        return user;
    }

    public User signUp(String firstName, String lastName, String email, String password) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if (email != null) email = email.toLowerCase();
        if (!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        user.setPassword(hashedPassword);

        return userRepo.save(user);
    }

    public Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + TOKEN_VALIDITY))
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
