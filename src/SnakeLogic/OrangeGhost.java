package SnakeLogic;

import SnakeGUI.Controller;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by brunofloerke on 19/06/2017.
 */
public class OrangeGhost extends Ghost {

    public OrangeGhost(Point position) {
        // Call super-constructor
        super(position);

        // Set color
        color = Color.ORANGE;
    }

    @Override
    protected void chase(PacMan pacMan) {
        //(greedy) best first search
        //get possible moves
        ArrayList<Field> possibleMoves = Controller.maze.getPossibleMoves(Controller.maze.getField(this));
        Point currentPosition = new Point(getX(), getY());
        double shortestDistance = currentPosition.distance(pacMan.getX(), pacMan.getY());
        Field bestMove = Controller.maze.getField(this);
        numberOfMovesChecked = 0;

        //calculate distance for all possible moves to pac
        for (Field move : possibleMoves) {
            Point point = new Point(move.x, move.y);
            numberOfMovesChecked++;
            if (point.distance(pacMan.getX(), pacMan.getY()) < shortestDistance) {
                bestMove = move;
                shortestDistance = point.distance(pacMan.getX(), pacMan.getY());
            }
            ;
        }

        //move to field with lowest distance
        move(bestMove);

        // print
        System.out.println("Orangie checked " + numberOfMovesChecked + " possible moves");
    }

    @Override
    protected void flee(PacMan pacMan) {
        //(greedy) best first search
        //get possible moves
        ArrayList<Field> possibleMoves = Controller.maze.getPossibleMoves(Controller.maze.getField(this));
        Point currentPosition = new Point(getX(), getY());
        double longestDistance = 0;
        Field bestMove = Controller.maze.getField(this);

        //calculate distance for all possible moves to pac
        for (Field move : possibleMoves) {
            Point point = new Point(move.x, move.y);
            if (point.distance(pacMan.getX(), pacMan.getY()) > longestDistance) {
                bestMove = move;
                longestDistance = point.distance(pacMan.getX(), pacMan.getY());
            }
            ;
        }

        //move to field with lowest distance
        move(bestMove);
    }
}

