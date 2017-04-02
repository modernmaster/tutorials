package uk.co.jamesmcguigan.patterns.behavioural.strategy;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private List<Double> drinks;
    private Billing strategy;

    public Customer(final Billing strategy) {
        this.drinks = new ArrayList<Double>();
        this.strategy = strategy;
    }

    public void add(final double price, final int quantity) {
        drinks.add(strategy.getActPrice(price * quantity));
    }

    // Payment of bill
    public void printBill() {
        double sum = 0;
        for (Double i : drinks) {
            sum += i;
        }
        System.out.println("Total due: " + sum);
        drinks.clear();
    }

    // Set Strategy
    public void setStrategy(final Billing strategy) {
        this.strategy = strategy;
    }
}
