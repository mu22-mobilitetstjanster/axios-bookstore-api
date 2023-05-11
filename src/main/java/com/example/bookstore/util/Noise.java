package com.example.bookstore.util;

import java.util.Random;

public class Noise {

  private static final Random random = new Random();

  public static void delay(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void random(int maxMs) {
    delay(random.nextInt(maxMs) + 1000);
  }
}
