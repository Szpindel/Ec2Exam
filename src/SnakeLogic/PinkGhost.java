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

        Queue<Field> queue = new LinkedList<>();
        List<Field> moveList = new LinkedList<>();
        Path initPath = (Path) Controller.maze.getField(this);
        Path nextPath = initPath;
        //add current position as first element in queue
        queue.add(Controller.maze.getField(this));
        seenFields.add(Controller.maze.getField(this).toString());
        while (!queue.isEmpty()) {                                              //loops while there are elements in queue
            Field currentField = queue.remove();                                //get and remove element from queue
            if (currentField.x == pacMan.getX() && currentField.y == pacMan.getY()) {   //if pac is found (base-case)
                moveList = new LinkedList<>();                                  //create new list of moves
                for (Path path = (Path) currentField; path != initPath; path = path.routePath) { //
                    moveList.add(path);
                }
            }

            // Check possible moves
            for (Field field : Controller.maze.getPossibleMoves(currentField)) {
                // check if already done
                if (!seenFields.contains(field.toString())) {
                    Path path = new Path((Path) field, (Path) currentField); // use constructor with routePath
                    queue.add(path);
                    seenFields.add(path.toString()); //only add if not seen before
                }
            }

            //calculate next move, which is first step of those steps that lead to pac using BFS
            for (int i = moveList.size(); i > 0 ; i--) {
                nextPath = (Path) moveList.get(moveList.size() - i);
            }
        }

        // Make the first step towards pacman
        move((Field) nextPath);

        // Clear memory of ghost
        seenFields.clear();

    }

    private void move(Field nextPath) {
        this.setX(nextPath.x);
        this.setY(nextPath.y);
    }
}
