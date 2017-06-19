package SnakeLogic;
import SnakeGUI.Controller;
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
    public void update(KeyCode keyPressed) {

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
        int random = (int) (Math.random() * 4);

        switch(random){
            case 0:
                if (Controller.maze.checkAvailability(new Point(this.getX(), this.getY()-1))){
                    this.setY(this.getY() - 1);
                }else{
                    beRandom();
                };
                break;
            case 1:
                if (Controller.maze.checkAvailability(new Point(this.getX() +1 , this.getY()))){
                    this.setX(this.getX() + 1);
                }else{
                    beRandom();
                };
                break;
            case 2:
                if (Controller.maze.checkAvailability(new Point(this.getX() - 1 , this.getY()))){
                    this.setX(this.getX() - 1);
                }else{
                    beRandom();
                };
                break;
            case 3:
                if (Controller.maze.checkAvailability(new Point(this.getX(), this.getY()+1))){
                //    System.out.println("move down");
                    this.setY(this.getY() + 1);
                }else{
                    beRandom();
                };
                break;
        }
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
    public void draw(GraphicsContext graphicsContext) {
        // draw Ghost
        graphicsContext.setFill(color);
        graphicsContext.fillOval(this.getX() * Controller.maze.getFieldWidth(), this.getY() * Controller.maze.getFieldHeight(), Controller.maze.getFieldWidth(), Controller.maze.getFieldHeight());
        graphicsContext.fillRect(this.getX() * Controller.maze.getFieldWidth(), (this.getY() * Controller.maze.getFieldHeight()) + Controller.maze.getFieldHeight()/2, Controller.maze.getFieldWidth(), (Controller.maze.getFieldHeight())/2);
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
