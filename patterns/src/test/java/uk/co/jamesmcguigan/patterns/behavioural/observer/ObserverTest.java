package uk.co.jamesmcguigan.patterns.behavioural.observer;

import org.junit.Test;

//Observer design pattern, class inheritance vs type inheritance

//An object's class defines how the object is implemented. In contrast,
// an object's type only refers to its interface. Class inheritance
// defines an object's implementation in terms of another object's
// implementation. Type inheritance describes when an object can be used
// in place of another.

public class ObserverTest {

    @Test
    public void testRegistrationOfAlarmDeviceds() {
        SensorSystem ss = new SensorSystem();
        ss.register(new Gates());
        ss.register(new Lighting());
        ss.register(new Surveillance());
        ss.soundTheAlarm();
    }
}
