package uk.co.jamesmcguigan.patterns.behavioural.command;

public class FlipDownCommand implements Command {
    private Light theLight;

    public FlipDownCommand(final Light light) {
        this.theLight = light;
    }

    //command
    public void execute() {
        theLight.turnOff();
    }
}
