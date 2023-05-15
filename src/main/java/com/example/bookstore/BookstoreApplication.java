package com.example.bookstore;

import com.example.bookstore.util.JwtUtil;
import com.example.bookstore.util.Noise;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@SpringBootApplication
public class BookstoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }

  @Bean
  public OncePerRequestFilter NoiseFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Noise.random(4000);
        filterChain.doFilter(request, response);
      }
    };
  }

  @Bean
  public OncePerRequestFilter authFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");

        if(request.getMethod().equals("OPTIONS")) {
          response.setStatus(HttpServletResponse.SC_OK);
        }
        else if(!request.getServletPath().contains("book")) {
          filterChain.doFilter(request, response);
        }
        else {
          String authHeader = request.getHeader("authorization");
          if(authHeader == null) {
            response.sendError(403);
          } else if (!JwtUtil.verify(authHeader.replace("Bearer ", ""))) {
            response.sendError(400);
          } else {
            filterChain.doFilter(request, response);
          }
        }
      }
    };
  }
}
