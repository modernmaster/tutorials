package uk.co.jamesmcguigan.patterns.behavioural.strategy;

import org.junit.Test;

public class BillingTest {

    @Test
    public void testBillingProcess() {
        Customer a = new Customer(new NormalBilling());

        // Normal billing
        a.add(1.0, 1);

        // Start Happy Hour
        a.setStrategy(new HappyHourBilling());
        a.add(1.0, 2);

        // New Customer
        Customer b = new Customer(new HappyHourBilling());
        b.add(0.8, 1);
        // The Customer pays
        a.printBill();

        // End Happy Hour
        b.setStrategy(new NormalBilling());
        b.add(1.3, 2);
        b.add(2.5, 1);
        b.printBill();

    }

}
