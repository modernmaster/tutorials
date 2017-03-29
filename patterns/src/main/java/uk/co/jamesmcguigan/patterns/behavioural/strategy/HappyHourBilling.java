package uk.co.jamesmcguigan.patterns.behavioural.strategy;

public class HappyHourBilling implements Billing {

    public double getActPrice(double rawPrice) {
        return rawPrice * 0.5;
    }
}
