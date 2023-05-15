package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDetails {
  private String username;
  private String password;
}
