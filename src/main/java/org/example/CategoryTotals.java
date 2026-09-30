package org.example;

import java.util.Map;
import java.util.TreeMap;

public class CategoryTotals {
    public static void main(String[] args) {
        TreeMap<String, Integer> totals = new TreeMap<>();
        addExpense(totals, "food", 20);
        addExpense(totals, "transport", 12);
        addExpense(totals, "food", 35);
        addExpense(totals, "rent", 35);

        for (Map.Entry<String, Integer> total : totals.entrySet()) {
            System.out.printf("%s: %d.%n", total.getKey(), total.getValue());
        }
    }

    static void addExpense(Map<String, Integer> map, String category, int amount) {
        map.put(category, map.getOrDefault(category, 0) + amount);
    }
}
