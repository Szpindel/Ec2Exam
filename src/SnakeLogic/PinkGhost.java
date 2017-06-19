package SnakeLogic;

import SnakeGUI.Controller;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        List<Field> moveList = new LinkedList<>();
        Path initPath = (Path) Controller.maze.getField(this);
        queue.add(Controller.maze.getField(this));
        seenFields.add(Controller.maze.getField(this).toString());

        while (!queue.isEmpty()) {
            Field currentField = queue.remove();

            if (currentField.x == pacMan.getX() && currentField.y == pacMan.getY()) {
                // Check if pacmans found (base-case)
                System.out.println("Found pacman at: " + currentField);

                moveList = new LinkedList<>();

                for(Path path = (Path) currentField; path != initPath; path = path.routePath){
                    moveList.add(path);
                }
                //move to (moveList.size()-1)
                System.out.println(moveList.get(moveList.size()-1));
            }

            for (Field field: Controller.maze.getPossibleMoves(currentField)) {
                if (!seenFields.contains(field.toString())) {
                    Path path = new Path((Path) field, (Path) currentField);
                    queue.add(path);
                    seenFields.add(path.toString());
                }
            }
        }
    }
}
