package uk.co.jamesmcguigan.patterns.behavioural.mediator;

public class Consumer extends Thread {
    // 3. Consumers are coupled only to the Mediator
    private Mediator med;
    private int    id;
    private static int num = 1;
    public Consumer( Mediator m ) {
        med = m;
        id = num++;
    }
    public void run() {
        while (true) {
            System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
        }
    }
}

