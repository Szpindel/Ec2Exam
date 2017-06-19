package SnakeLogic;

import SnakeGUI.Controller;
import javafx.scene.paint.Color;

import java.awt.*;

import java.util.ArrayList;

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
        //breadth first searching

        // get possible moves
        ArrayList<Field> possibleMoves = Controller.maze.getPossibleMoves(this);


        // check if Fields have been visited

        //

    }

}
