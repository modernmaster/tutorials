package uk.co.jamesmcguigan.patterns.structural.module;

import org.junit.Test;

public class ModuleTest {
    public static MainModule console = null;

    @Test
    public void testExecutionOfModule() {
        prepare();
        execute();
        unprepare();
    }

    public static void prepare() {
        console = MainModule.getSingleton();

        console.prepare();
    }

    public static void unprepare() {
        console.unprepare();
    }

    public static void execute() {
        console.printString("Hello World");
        console.printNewLine();
        console.scanNewLine();
    }
}