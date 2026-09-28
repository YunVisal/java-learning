package org.example;

import java.util.Map;
import java.util.TreeMap;

public class CategoryTotals {
    public static void main(String[] args) {
        TreeMap<String, Integer> totals = new TreeMap<>();
        totals.put("food", totals.getOrDefault("food", 0) + 20);
        totals.put("transport", totals.getOrDefault("transport", 0) + 12);
        totals.put("food", totals.getOrDefault("food", 0) + 35);
        totals.put("rent", totals.getOrDefault("rent", 0) + 35);

        for (Map.Entry<String, Integer> total : totals.entrySet()) {
            System.out.printf("%s: %d.%n", total.getKey(), total.getValue());
        }
    }
}
