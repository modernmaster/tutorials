package uk.co.jamesmcguigan.patterns.behavioural.strategy;

public class HappyHourBilling implements Billing {

    private static final double DISCOUNT = 0.5;

    public double getActPrice(final double rawPrice) {
        return rawPrice * DISCOUNT;
    }
}

