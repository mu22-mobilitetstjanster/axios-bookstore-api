package com.example.bookstore.controller;

import com.example.bookstore.model.AuthDetails;
import com.example.bookstore.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

@RestController
@RequestMapping("/auth/*")
public class AuthController {

  @PostMapping("login")
  public ResponseEntity<String> login(@RequestBody AuthDetails authDetails) {

    String token = JwtUtil.sign(authDetails.getUsername());

    return ResponseEntity.ok(token);
  }
}
