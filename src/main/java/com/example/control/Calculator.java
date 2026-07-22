package com.example.control;

import java.lang.String;
import java.lang.System;

public class Calculator {
  public void calculate() {
    int total = 0;
    for (int i = 1; i <= 5; i++) {
      total += i;
    }
    if (total > 10) {
      System.out.println("Greater");
    } else {
      System.out.println("Smaller");
    }
  }

  public static void main(String[] args) {
    new Calculator().calculate();
  }
}
