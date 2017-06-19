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
                break;
            case LEFT:
                if (Controller.maze.checkAvailability(this, 1)){
                super.moveLeft();
                }
                break;
            case RIGHT:
                if (Controller.maze.checkAvailability(this, 2)){
                    super.moveRight();
                }
                break;
            case UP:
                if (Controller.maze.checkAvailability(this, 0)){
                    super.moveUp();
                }
                break;
        }

        //play soundeffects
//        Media sound = new Media(new File("src/Sounds/pacChompBru.mp3").toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
    }

    public boolean isSuperPowered(){
        return superPowered;
        //superPoweredTimer here
    }


    public void makeSuperPowered() {
        superPowered = true;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        // draw PacMan
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(this.getX() * Controller.maze.getFieldWidth(), this.getY() * Controller.maze.getFieldHeight(),
                                    Controller.maze.getFieldWidth(), Controller.maze.getFieldHeight());
    }


    public void increaseScore(int increment){
        score+=increment;
    }


    public int getScore() {
        return score;
    }


}
