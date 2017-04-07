package uk.co.jamesmcguigan.patterns.behavioural.mediator;

public class Mediator {
    // 4. The Mediator arbitrates
    private boolean slotFull = false;
    private int number;

    public synchronized void storeMessage(final int num) {
        // no room for another message
        while (slotFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        slotFull = true;
        number = num;
        notifyAll();
    }

    public synchronized int retrieveMessage() {
        // no message to retrieve
        while (!slotFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        slotFull = false;
        notifyAll();
        return number;
    }
}
