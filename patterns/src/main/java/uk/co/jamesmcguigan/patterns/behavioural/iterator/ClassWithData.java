package uk.co.jamesmcguigan.patterns.behavioural.iterator;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class ClassWithData {
    private Hashtable ht = new Hashtable();

    // 1. Design an internal "iterator" class for the "collection" class
    public static class Iterator {
        private ClassWithData set;
        private Enumeration e;
        private Integer current;

        public Iterator(ClassWithData in) {
            set = in;
        }

        public void first() {
            e = set.ht.keys();
            next();
        }

        public boolean isDone() {
            return current == null;
        }

        public int currentItem() {
            return current.intValue();
        }

        public void next() {
            try {
                current = (Integer) e.nextElement();
            } catch (NoSuchElementException e) {
                current = null;
            }
        }
    }

    public void add(int in) {
        ht.put(new Integer(in), "null");
    }

    public boolean isMember(int i) {
        return ht.containsKey(new Integer(i));
    }

    public Hashtable getHashtable() {
        return ht;
    }

    // 2. Add a createIterator() member to the collection class
    public Iterator createIterator() {
        return new Iterator(this);
    }
}
