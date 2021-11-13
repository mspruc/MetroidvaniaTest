package Components;

import org.joml.Vector2f;

public class PhysicsComponent {
    //float accelerationX;
    Vector2f acceleration;
    Vector2f velocity;

    public void update(Vector2f position){
        position.add(velocity);

        velocity.add(acceleration);

        velocity.mul(0.8f); //air friction

        acceleration.zero();
    }

    public Vector2f applyForce(Vector2f forceVector, Vector2f acceleration, Vector2f frictionVector){

        return new Vector2f();
    }

    public boolean isGrounded(){

        return false;
    }
}
