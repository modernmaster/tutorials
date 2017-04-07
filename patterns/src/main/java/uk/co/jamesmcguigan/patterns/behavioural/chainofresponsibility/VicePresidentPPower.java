package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public class VicePresidentPPower extends PurchasePower {
    private static final double ALLOWABLE = 40 * BASE;

    public void processRequest(final PurchaseRequest request) {
        if (request.getAmount() < ALLOWABLE) {
            System.out.println("Vice President will approve $" + request.getAmount());

        } else if (getSuccessor() != null) {
            getSuccessor().processRequest(request);
        }
    }
}
