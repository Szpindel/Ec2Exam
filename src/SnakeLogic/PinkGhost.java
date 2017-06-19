package SnakeLogic;

import javafx.scene.paint.Color;

import java.awt.*;

/**
 * Created by brunofloerke on 19/06/2017.
 */
public class PinkGhost extends Ghost{

    public PinkGhost(Point position){
        // Call super-constructor
        super(position);

        // Set color
        color = Color.PINK;
    }

    @Override
    protected void chase() {

    }

}
