package uk.co.jamesmcguigan.patterns.behavioural.mediator;

public class Consumer extends Thread {
    private static int num = 1;
    // 3. Consumers are coupled only to the Mediator
    private Mediator med;
    private int id;

    public Consumer(final Mediator m) {
        med = m;
        id = num++;
    }

    public void run() {
        while (true) {
            System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
        }
    }
}

