package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.Random;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */

//fix this "tale" business
public class PacMan implements GameObject {

    private int X;
    private int Y;
    Random random = new Random();
    private Maze maze;
    private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;

    public PacMan(Point position) {
        this.maze = maze;
        X = position.x;
        Y = position.y;
    }

    @Override
    public void update(KeyCode keyPressed, Maze maze) {


        switch (keyPressed) {
            case DOWN:
                if (maze.checkAvailability(new Point(this.getX(), this.getY() + 1))) {
                    this.setY(this.getY() + 1);
                }
                break;
            case LEFT:
                if (maze.checkAvailability(new Point(this.getX() - 1, this.getY()))) {
                    this.setX(this.getX() - 1);
                }
                break;
            case RIGHT:
                if (maze.checkAvailability(new Point(this.getX() + 1, this.getY()))) {
                    this.setX(this.getX() + 1);
                }
                break;
            case UP:
                if (maze.checkAvailability(new Point(this.getX(), this.getY() - 1))) {
                    this.setY(this.getY() - 1);
                }
                break;
        }

        // Check if wrap-around
        if(getX() <= 0){
            this.setX(maze.getWidth());
        }else if(getX() >= maze.getWidth()){
            this.setX(0);

        }


    }

    @Override
    public void draw(GraphicsContext graphicsContext, Maze maze) {
        // draw PacMan
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight());

        System.out.println(this.getX() + " " + this.getY());
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

//    public void addToTale() {
//        JakeTalePiece jtp = new JakeTalePiece();
//        if (taleFirst == null)
//        {
//            taleFirst = jtp;
//            taleLast = jtp;
//        }
//        else
//        {
//            taleLast.setNext(jtp);
//            taleLast = jtp;
//        }
//
//    }
}
