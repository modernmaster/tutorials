package uk.co.jamesmcguigan.patterns.behavioural.command;

import org.junit.Test;

public class CommandTest {

    @Test
    public void testPressSwitchOn() {
        int switchOn = 1;
        Light lamp = new Light();
        Command switchUp = new FlipUpCommand(lamp);
        Command switchDown = new FlipDownCommand(lamp);

        Switch mySwitch = new Switch();

        switch(switchOn) {
            case 1:
                mySwitch.storeAndExecute(switchUp);
                break;
            case 0:
                mySwitch.storeAndExecute(switchDown);
                break;
            default:
                System.err.println("Argument \"ON\" or \"OFF\" is required.");
                System.exit(-1);
        }

    }

    @Test
    public void testPressSwitchOff() {
        int switchOff = 0;
        Light lamp = new Light();
        Command switchUp = new FlipUpCommand(lamp);
        Command switchDown = new FlipDownCommand(lamp);

        Switch mySwitch = new Switch();

        switch(switchOff) {
            case 1:
                mySwitch.storeAndExecute(switchUp);
                break;
            case 0:
                mySwitch.storeAndExecute(switchDown);
                break;
            default:
                System.err.println("Argument \"ON\" or \"OFF\" is required.");
                System.exit(-1);
        }

    }

}
