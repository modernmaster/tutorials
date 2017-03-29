package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

public class VicePresidentPPower extends PurchasePower {
    private final double ALLOWABLE = 40 * BASE;

    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < ALLOWABLE) {
            System.out.println("Vice President will approve $" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}
