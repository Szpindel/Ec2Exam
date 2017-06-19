package SnakeLogic;

import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * Created by brunofloerke on 19/06/2017.
 */
public class RedGhost extends Ghost{

    public RedGhost(Point position) {
        // Call super-constructor
        super(position);

        // Set color
        color = Color.RED;
    }

    @Override
    protected void chase(PacMan pacMan) {

    }
}