package Components;

import org.joml.Vector2f;

public class GfxComponent {

    private Vector2f drawDimensions;


    /**
     * Draws a texture at the given position
     * @param position position where the c
     */
    public void draw(Vector2f position){



    }

    public void setDrawDimensions(Vector2f newDimensions){
        this.drawDimensions = newDimensions;
    }

    /**
     * Get the dimensions of the drawn texture.
     * @return null, or a 2d vector representing x-width and y-height respectively.
     */
    public Vector2f getDrawDimensions(){
        return drawDimensions;
    }

    public void getTexture(){}
    public void setTexture(){}
    public void offsetDrawLocation(){}
}
