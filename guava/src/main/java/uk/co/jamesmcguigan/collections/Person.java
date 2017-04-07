package uk.co.jamesmcguigan.collections;

import com.google.common.collect.ComparisonChain;

public class Person implements Comparable<Person> {
    private String lastName;
    private String firstName;
    private int zipCode;

    public Person(final String lastName, final String firstName,
                  final int zipCode) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.zipCode = zipCode;
    }

    public int compareTo(final Person that) {
        return ComparisonChain.start()
                .compare(this.lastName, that.lastName)
                .compare(this.zipCode, that.zipCode)
//                .compare(this.Sex, that.Sex, Ordering.natural().nullsLast())
                .result();
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

}
