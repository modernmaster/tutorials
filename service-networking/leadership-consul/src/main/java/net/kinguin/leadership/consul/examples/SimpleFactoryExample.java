package net.kinguin.leadership.consul.examples;

import net.kinguin.leadership.consul.factory.SimpleConsulClusterFactory;

import java.util.concurrent.ExecutionException;

public class SimpleFactoryExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new SimpleConsulClusterFactory()
            .mode(SimpleConsulClusterFactory.MODE_MULTI)
            .debug(true)
            .build()
            .asObservable()
            .subscribe(i -> System.out.println(i));
    }
}
