package org.example;

import java.math.BigDecimal;

public class MoneyDemo {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("0.2");
        System.out.println(a.add(b));
    }
}
