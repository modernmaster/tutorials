package uk.co.jamesmcguigan.patterns.behavioural.strategy;

public class NormalBilling implements Billing {

    public double getActPrice(double rawPrice) {
        return rawPrice;
    }
}
