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

/**
 * Created by Ebbe Vang on 21-02-2017.
 */

//fix this "tale" business
public class PacMan extends GameObject {

    //    private int X;
//    private int Y;
    private int score;
    private boolean hasBegun = false;
    private int superPowerTimer = 0;
    private int numberOfLives = 3;
    // Random random = new Random();
    // private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;
    private boolean superPowered = false;

    public PacMan(Point pacPosition) {
        super(pacPosition);
    }

    public void update(KeyCode keyPressed) {
        switch (keyPressed) {
            case DOWN:
                if (Controller.maze.checkAvailability(this, 3)) {
                    super.moveDown();
                }
                hasBegun = true;
                break;
            case LEFT:
                if (Controller.maze.checkAvailability(this, 1)) {
                    super.moveLeft();
                }
                hasBegun = true;
                break;
            case RIGHT:
                if (Controller.maze.checkAvailability(this, 2)) {
                    super.moveRight();
                }
                hasBegun = true;
                break;
            case UP:
                if (Controller.maze.checkAvailability(this, 0)) {
                    super.moveUp();
                }
                hasBegun = true;
                break;
        }

        // Update power structure
        if (isSuperPowered()) {
            superPowerTimer--;
        }

        if (superPowerTimer < 0) {
            disableSuperPower();
        }
    }

    public boolean isSuperPowered() {
        return superPowered;
    }

    public boolean isHasBegun() {
        return hasBegun;
    }


    public void enableSuperPower() {
        superPowered = true;
        superPowerTimer = 10;
        //System.out.println("Superpowered");
    }

    public void disableSuperPower() {
        superPowered = false;
        //System.out.println("unpowered");

    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        // draw PacMan
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(this.getX() * Controller.maze.getFieldWidth(), this.getY() * Controller.maze.getFieldHeight(),
                Controller.maze.getFieldWidth(), Controller.maze.getFieldHeight());
    }


    public void increaseScore(int increment) {
        score += increment;
    }


    public int getScore() {
        return score;
    }


    public void removeLife() {
        numberOfLives--;
        if (numberOfLives < 0) {
            System.out.println("GAME OVER");
            System.exit(0);
        } else {
            super.resetToSpawn();
        }
    }
}
