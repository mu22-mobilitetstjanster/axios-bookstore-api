package com.example.bookstore.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
  private final static String SECRET = "bananpenguinsawesomearecatstoocarrotcakeistastybutsoismudcakeandcoffe";

  public static String sign(String username) {
    Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    return Jwts
            .builder()
            .claim("username", username)
            .signWith(key)
            .compact();
  }

  public static boolean verify(String token) {
    Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    return Jwts.parserBuilder().setSigningKey(key).build().isSigned(token);
  }
}
