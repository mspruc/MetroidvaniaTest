package GUI;

import org.joml.Vector2f;

public class MouseListener {

    private Vector2f mousePos = new Vector2f(0,0);
    private Vector2f scrollWhl = new Vector2f(0,0);

    private boolean isEnabled = true;
    private boolean[] buttonPressed;
    private boolean isMoving = false;

    private static MouseListener mouseListener;

    private MouseListener(){


    }

    public static MouseListener getInstance(){
        if (MouseListener.mouseListener == null){
            mouseListener = new MouseListener();
        }

        return MouseListener.mouseListener;
    }
}
