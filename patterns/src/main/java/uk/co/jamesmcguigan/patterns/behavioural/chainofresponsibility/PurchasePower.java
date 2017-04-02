package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public abstract class PurchasePower {
    static final double BASE = 500;
    private PurchasePower successor;

    public abstract void processRequest(PurchaseRequest request);

    void setSuccessor(final PurchasePower successor) {
        this.successor = successor;
    }

    public PurchasePower getSuccessor() {
        return successor;
    }
}
