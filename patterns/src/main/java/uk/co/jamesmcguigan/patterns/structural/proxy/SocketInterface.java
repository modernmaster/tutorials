package uk.co.jamesmcguigan.patterns.structural.proxy;

public interface SocketInterface {
    String readLine();

    void writeLine(String str);

    void dispose();
}


