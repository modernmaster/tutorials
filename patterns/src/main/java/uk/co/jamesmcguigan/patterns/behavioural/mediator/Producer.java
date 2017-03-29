package uk.co.jamesmcguigan.patterns.behavioural.mediator;

public class Producer extends Thread {
    // 2. Producers are coupled only to the Mediator
    private Mediator med;
    private int    id;
    private static int num = 1;
    public Producer( Mediator m ) {
        med = m;
        id = num++;
    }
    public void run() {
        int num;
        while (true) {
            med.storeMessage( num = (int)(Math.random()*100) );
            System.out.print( "p" + id + "-" + num + "  " );
        }
    }
}
