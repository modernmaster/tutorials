package uk.co.jamesmcguigan.patterns.behavioural.command;

public class FlipUpCommand implements Command {
    private Light theLight;

    public FlipUpCommand(final Light light) {
        this.theLight = light;
    }

    // Command
    public void execute() {
        theLight.turnOn();
    }
}
