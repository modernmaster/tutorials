package uk.co.jamesmcguigan.patterns.creational.objectpool;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class ObjectPool<T> {
    private static final long EXPIRATION_TIME = 30000;

    private Hashtable<T, Long> locked, unlocked;

    public ObjectPool() {
        locked = new Hashtable<T, Long>();
        unlocked = new Hashtable<T, Long>();
    }

    protected abstract T create();

    public abstract boolean validate(final T o);

    public abstract void expire(final T o);

    public synchronized T checkOut() {
        long now = System.currentTimeMillis();
        T t;
        if (unlocked.size() > 0) {
            Enumeration<T> e = unlocked.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlocked.get(t)) > EXPIRATION_TIME) {
                    // object has expired
                    unlocked.remove(t);
                    expire(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlocked.remove(t);
                        locked.put(t, now);
                        return (t);
                    } else {
                        // object failed validation
                        unlocked.remove(t);
                        expire(t);
                        t = null;
                    }
                }
            }
        }
        // no objects available, create a new one
        t = create();
        locked.put(t, now);
        return (t);
    }

    public synchronized void checkIn(final T t) {
        locked.remove(t);
        unlocked.put(t, System.currentTimeMillis());
    }
}
