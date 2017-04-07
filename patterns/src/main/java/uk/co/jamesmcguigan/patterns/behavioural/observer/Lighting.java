package uk.co.jamesmcguigan.patterns.behavioural.observer;

public class Lighting implements AlarmListener {
    public void alarm() {
        System.out.println("lights up");
    }
}
