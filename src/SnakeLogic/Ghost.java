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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * Created by brunofloerke on 16/06/2017.
 */
public abstract class Ghost extends GameObject {
    protected Color color;
    HashMap<String, Field> visitedFields = new HashMap<>();

    private int score;
    // Random random = new Random();

    // private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;

    public Ghost(Point position) {
        super(position);
    }

    public void update(PacMan pacMan) {
        // Add current field as visited
        Field currentField = Controller.maze.getField(getX(),getY());
        visitedFields.put(currentField.toString(), currentField);

//        beRandom();

        // Change behaviour
        changeBehaviour();

        // Print info
        if (pacMan.isSuperPowered()) {
            System.out.println("flee!!");
        }

        // Check kill conditions
        checkKillConditions(pacMan);
    }

    protected void changeBehaviour() {
        // TEMP
//        beRandom();

        chase();
    }

    ;

    protected void beRandom() {
        int random = (int) (Math.random() * 4);

        if(Controller.maze.checkAvailability(this, random)){
            // Move
            switch(random){
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveLeft();
                    break;
                case 2:
                    moveRight();
                    break;
                case 3:
                    moveDown();
                    break;
            }
        }else{
            // try again
            beRandom();
        }



    }

    protected void runAway() {

    }

    abstract protected void chase();


    private void checkKillConditions(PacMan pacMan) {
        if (this.getX() == pacMan.getX() && this.getY() == pacMan.getY()) {
            if (pacMan.isSuperPowered() == true) {
                //respawn Ghost
                resetToSpawn();
                System.out.println("ghost respawns");

                //increase score
            } else {
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
        graphicsContext.fillRect(this.getX() * Controller.maze.getFieldWidth(), (this.getY() * Controller.maze.getFieldHeight()) + Controller.maze.getFieldHeight() / 2, Controller.maze.getFieldWidth(), (Controller.maze.getFieldHeight()) / 2);
    }

    public void resetToSpawn() {
        // Potential error when casting
        this.setY((int) super.initPosition.getY());
        this.setX((int) super.initPosition.getX());
    }

}