package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public class PurchaseRequest {
    private double amount;
    private String purpose;

    public PurchaseRequest(final double amount, final String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amt) {
        amount = amt;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(final String reason) {
        purpose = reason;
    }
}
