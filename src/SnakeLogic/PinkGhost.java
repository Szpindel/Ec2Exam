package SnakeLogic;

import SnakeGUI.Controller;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by brunofloerke on 19/06/2017.
 */
public class PinkGhost extends Ghost {

    public PinkGhost(Point position) {
        // Call super-constructor
        super(position);

        // Set color
        color = Color.PINK;
    }

    @Override
    protected void chase(PacMan pacMan) {
        //breadth first searching

        // get possible moves
        ArrayList<Field> possibleMoves = Controller.maze.getPossibleMoves(Controller.maze.getField(this));

        // check if Fields have been visited
//            for (Field field : possibleMoves) {
//                if (visitedFields.containsKey(field.toString()) == false) {
//                    // Mark field as visited
//                    visitedFields.put(field.toString(), field);
//
//                    // Add to queue
//                    queue.add(field);
//                }
//            }


        Queue<Field> queue = new LinkedList<>();
        queue.add(Controller.maze.getField(this));
        seenFields.add(Controller.maze.getField(this).toString());

        while (!queue.isEmpty()) {
            Field currentField = queue.remove();
            System.out.println(queue.size());

            if (currentField.x == pacMan.getX() && currentField.y == pacMan.getY()) {
                // Check if pacmans found (base-case)
                System.out.println("Found pacman at: " + currentField);
                System.exit(0);
            }

            for (Field field: Controller.maze.getPossibleMoves(currentField)) {
                if (!seenFields.contains(field.toString())) {
                    queue.add(field);
                    seenFields.add(field.toString());
                }
            }
        }
    }
}
