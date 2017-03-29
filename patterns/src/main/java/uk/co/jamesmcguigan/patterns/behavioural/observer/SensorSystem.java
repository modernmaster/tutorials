package uk.co.jamesmcguigan.patterns.behavioural.observer;

public class SensorSystem {
    private java.util.Vector listeners = new java.util.Vector();

    public void register(AlarmListener al) {
        listeners.addElement(al);
    }

    public void soundTheAlarm() {
        for (java.util.Enumeration e = listeners.elements(); e.hasMoreElements(); )
            ((AlarmListener) e.nextElement()).alarm();
    }
}
