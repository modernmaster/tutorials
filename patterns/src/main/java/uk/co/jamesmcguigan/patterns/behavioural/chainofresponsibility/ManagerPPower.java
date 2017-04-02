package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public class ManagerPPower extends PurchasePower {
    private static final double ALLOWABLE = 10 * BASE;

    public void processRequest(final PurchaseRequest request) {
        if (request.getAmount() < ALLOWABLE) {
            System.out.println("Manager will approve $" + request.getAmount());
        } else if (getSuccessor() != null) {
            getSuccessor().processRequest(request);
        }
    }
}
