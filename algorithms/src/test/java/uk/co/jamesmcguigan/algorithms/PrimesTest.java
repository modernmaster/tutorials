package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimesTest {

    private Primes primes;

    @Before
    public void setUp() {
        primes = new Primes();
    }

    @Test
    public void test19IsPrime() {
        int n = 19;
        boolean result = primes.isPrime(n);
        assertTrue(result);
    }

    @Test
    public void test15IsNotPrime() {
        int n = 15;
        boolean result = primes.isPrime(n);
        assertFalse(result);
    }

    @Test
    public void test1IsNotPrime() {
        int n = 1;
        boolean result = primes.isPrime(n);
        assertFalse(result);
    }

    @Test
    public void testShouldCalculateThe2PrimeNumber()  {
        int n = 2;
        int expectedResult = 3;
        int result = primes.calculateNPrime(n);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testShouldCalculateThe15PrimeNumber()  {
        int n = 15;
        int expectedResult = 47;
        int result = primes.calculateNPrime(n);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testShouldCalculateThe20PrimeNumber()  {
        int n = 20;
        int expectedResult = 71;
        int result = primes.calculateNPrime(n);
        assertEquals(expectedResult, result);
    }
}
