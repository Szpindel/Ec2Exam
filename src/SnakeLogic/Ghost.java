package SnakeLogic;
import SnakeGUI.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.Random;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * Created by brunofloerke on 16/06/2017.
 */
public class Ghost implements GameObject {

    private int X;
    private int Y;
    private int score;
    // Random random = new Random();
    private Maze maze;
    // private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;

    public Ghost(Point ghostPosition) {
        this.maze = maze;
        X = ghostPosition.x;
        Y = ghostPosition.y;
    }



    @Override
    public void update(KeyCode keyPressed, Maze maze) {

    }

    @Override
    public void draw(GraphicsContext graphicsContext, Maze maze) {
        // draw Ghost
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight());
        graphicsContext.fillRect(this.getX() * maze.getFieldWidth(), (this.getY() * maze.getFieldHeight()) + maze.getFieldHeight()/2, maze.getFieldWidth(), (maze.getFieldHeight())/2);
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
}
