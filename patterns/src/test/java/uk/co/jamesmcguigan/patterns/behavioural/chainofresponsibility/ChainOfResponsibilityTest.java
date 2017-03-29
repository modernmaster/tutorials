package uk.co.jamesmcguigan.patterns.behavioural.chainofresponsibility;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChainOfResponsibilityTest {
    @Test
    public void testCheckAuthority() {
        ManagerPPower manager = new ManagerPPower();
        DirectorPPower director = new DirectorPPower();
        VicePresidentPPower vp = new VicePresidentPPower();
        PresidentPPower president = new PresidentPPower();
        manager.setSuccessor(director);
        director.setSuccessor(vp);
        vp.setSuccessor(president);

        double amountToCheck = 10000000.00;
        String purpose = "General";
        manager.processRequest(new PurchaseRequest(amountToCheck, purpose));
    }
}
