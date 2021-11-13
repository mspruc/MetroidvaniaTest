package Entity;
import Components.PhysicsComponent;

public class Entity{

    float posX, posY;
    private PhysicsComponent physicsComponent;

    public PhysicsComponent getPhysicsComponent(){
        return physicsComponent;
    }


    Entity() {

    }


    Entity(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
