package com.myExpense.myExpenseTracker.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.myExpense.myExpenseTracker.Constants.API_SECRET_KEY;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null) {
            try {
                Claims claims = Jwts.parser().setSigningKey(API_SECRET_KEY).parseClaimsJws(authHeader).getBody();
                String userId = claims.get("id").toString();
                httpRequest.setAttribute("userId", userId);
            } catch (Exception e) {
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
                return;
            }
        } else {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token is missing");
            return;
        }

        chain.doFilter(request, response);
    }
}
