package uk.co.jamesmcguigan.patterns.structural.proxy;

import org.junit.Ignore;
import org.junit.Test;

public class ProxyTest {

    @Test
    @Ignore
    public void testProxyObjectForSocket() {

        // 3. The client deals with the wrapper
        boolean wait = true;


        SocketInterface socket = new SocketProxy( "127.0.0.1", 8189, wait);

        String  str = null;
        boolean skip = true;
        while (true) {
            if (skip) {
                skip = ! skip;
            }
            else {
                str = socket.readLine();
                System.out.println( "Receive - " + str );  // java ProxyDemo first
                if (str.equals("quit")) break;             // Receive - 123 456
            }                                            // Send ---- 234 567
            System.out.print( "Send ---- " );            // Receive - 345 678
            str = "Test send reply";                        //
            socket.writeLine( str );                     // java ProxyDemo second
            if (str.equals("quit")) break;               // Send ---- 123 456
        }                                              // Receive - 234 567
        socket.dispose();                              // Send ---- 345 678
    }
}
