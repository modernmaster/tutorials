package uk.co.jamesmcguigan.patterns.behavioural.observer;

public class Gates implements AlarmListener {
    public void alarm() {
        System.out.println("gates close");
    }
}

