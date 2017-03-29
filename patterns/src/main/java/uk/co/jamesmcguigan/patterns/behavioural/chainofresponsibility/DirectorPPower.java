package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public class DirectorPPower extends PurchasePower {
    private final double ALLOWABLE = 20 * BASE;

    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < ALLOWABLE) {
            System.out.println("Director will approve $" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}
