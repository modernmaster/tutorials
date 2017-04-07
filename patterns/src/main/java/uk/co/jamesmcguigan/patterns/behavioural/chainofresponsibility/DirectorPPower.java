package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public class DirectorPPower extends PurchasePower {
    private static final double ALLOWABLE = 20 * BASE;

    public void processRequest(final PurchaseRequest request) {
        if (request.getAmount() < ALLOWABLE) {
            System.out.println("Director will approve $" + request.getAmount());
        } else if (getSuccessor() != null) {
            getSuccessor().processRequest(request);
        }
    }
}
