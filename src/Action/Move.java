package Action;

public class Move implements Action{


    @Override
    public void execute() {
        this.source.getPhysicsComponent();
    }

    @Override
    public void undo() {

    }
}
