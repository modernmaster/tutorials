package uk.co.jamesmcguigan.patterns.behavioural.mediator;

public class Producer extends Thread {
    private static int num = 1;
    private static final int LIMIT = 100;
    // 2. Producers are coupled only to the Mediator
    private Mediator med;
    private int id;

    public Producer(final Mediator m) {
        med = m;
        id = num++;
    }

    public void run() {
        int num;
        while (true) {
            num = (int) (Math.random() * LIMIT);
            med.storeMessage(num);
            System.out.print("p" + id + "-" + num + "  ");
        }
    }
}
