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
public class PacMan  implements GameObject{

    private int X;
    private int Y;
    Random random = new Random();
    private Maze maze;
    private JakeTalePiece taleFirst;
 //   private JakeTalePiece taleLast;

    public PacMan(Point position)
    {
        this.maze = maze;
        X = position.x;
        Y = position.y;
    }

    @Override
    public void update(KeyCode keyPressed) {

        if (taleFirst != null) {
            taleFirst.update(keyPressed);
            taleFirst.setX(X);
            taleFirst.setY(Y);

        }

        switch (keyPressed)
        {
            case DOWN:
                this.setY(this.getY() + 1);
                break;
            case LEFT:
                this.setX(this.getX() - 1);
                break;
            case RIGHT:
                this.setX(this.getX() + 1);
                break;
            case UP:
                this.setY(this.getY() - 1);
                break;
        }
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Maze maze) {
        // draw PacMan
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight());
        //draw tale
        if (taleFirst != null) {
            taleFirst.draw(graphicsContext, maze);
        }
    }

    public int getX() {
        return X;
    }

    public void setX(int x) { X = x; }

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
