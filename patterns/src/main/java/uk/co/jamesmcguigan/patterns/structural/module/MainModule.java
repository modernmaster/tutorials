package uk.co.jamesmcguigan.patterns.structural.module;

import java.io.InputStream;
import java.io.PrintStream;

public final class MainModule {

    private static MainModule singleton = null;

    private InputStream input = null;
    private PrintStream output = null;
    private PrintStream error = null;

    public MainModule() {
        // does nothing on purpose !!!
    }

    public static MainModule getSingleton() {
        if (MainModule.singleton == null) {
            MainModule.singleton = new MainModule();
        }

        return MainModule.singleton;
    }

    public void prepare() {
        //instantiate
//        this.input = new InputStream();
//        this.output = new PrintStream();
//        this.error = new PrintStream();
    }

    public void unprepare() {
        this.output = null;
        this.input = null;
        this.error = null;
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printString(final String value) {
        System.out.print(value);
    }

    public void printInteger(final int value) {
        System.out.print(value);
    }

    public void printBoolean(final boolean value) {
        System.out.print(value);
    }

    public void scanNewLine() {
        // to-do: ...
    }

    public void scanString(final String value) {
        // to-do: ...
    }

    public void scanInteger(final int value) {
        // to-do: ...
    }

    public void scanBoolean(final boolean value) {
        // to-do: ...
    }

}
