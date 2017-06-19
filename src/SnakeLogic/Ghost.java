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
public abstract class Ghost implements GameObject {
    protected Color color;
    private int X;
    private int Y;
    private Point initPosition;
    private int score;
    // Random random = new Random();

    // private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;

    public Ghost(Point ghostPosition) {
        this.initPosition = ghostPosition;
        X = ghostPosition.x;
        Y = ghostPosition.y;
    }


    @Override
    public void update(KeyCode keyPressed, Maze maze) {

    }

    public void update(PacMan pacMan){
        // Change behaviour
        changeBehaviour();

        // Print info
        if (pacMan.isSuperPowered()){
            System.out.println("flee!!");
        }

        // Check kill conditions
        checkKillConditions(pacMan);
    }

    protected void changeBehaviour(){
        // TEMP
        beRandom();
    };

    protected void beRandom(){

    }

    protected void runAway(){

    }

    abstract protected void chase();


    private void checkKillConditions(PacMan pacMan){
        if (this.getX() == pacMan.getX() && this.getY() == pacMan.getY()){
            if (pacMan.isSuperPowered() == true){
                //respawn Ghost
                resetToSpawn();
                System.out.println("ghost respawns");

                //increase score
            }else{
                System.out.println("Pacman dies");
                //Game Over screen; System.Exit(0)
            }
        }
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Maze maze) {
        // draw Ghost
        graphicsContext.setFill(color);
        graphicsContext.fillOval(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight());
        graphicsContext.fillRect(this.getX() * maze.getFieldWidth(), (this.getY() * maze.getFieldHeight()) + maze.getFieldHeight()/2, maze.getFieldWidth(), (maze.getFieldHeight())/2);
    }

    public void resetToSpawn(){
        // Potential error when casting
        this.setY((int) initPosition.getY());
        this.setX((int) initPosition.getX());
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
