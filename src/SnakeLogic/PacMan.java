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

/**
 * Created by Ebbe Vang on 21-02-2017.
 */

//fix this "tale" business
public class PacMan implements GameObject {

    private int X;
    private int Y;
    private int score;
   // Random random = new Random();
    private Maze maze;
   // private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;



    public PacMan(Point pacPosition) {
        this.maze = maze;
        X = pacPosition.x;
        Y = pacPosition.y;
    }

    @Override
    public void update(KeyCode keyPressed, Maze maze) {
        switch (keyPressed) {
            case DOWN:
                if (maze.checkAvailability(new Point(this.getX(), this.getY() + 1))) {
                    this.setY(this.getY() + 1);
                    System.out.print(this.getX() + "/" + (this.getY()+1));
                }
                break;
            case LEFT:
                if (maze.checkAvailability(new Point(this.getX() - 1, this.getY()))) {
                    this.setX(this.getX() - 1);
                    System.out.println((this.getX()-1) + "/" + this.getY());
                }
                break;
            case RIGHT:
                if (maze.checkAvailability(new Point(this.getX() + 1, this.getY()))) {
                    this.setX(this.getX() + 1);
                    System.out.println((this.getX()+1) + "/" + this.getY());
                }
                break;
            case UP:
                if (maze.checkAvailability(new Point(this.getX(), this.getY() - 1))) {
                    this.setY(this.getY() - 1);
                    System.out.println(this.getX() + "/" + (this.getY()-1));
                }
                break;
        }

        // Check if wrap-around (needs more thoughts)
        if(getX() <= 0){
            this.setX(maze.getWidth());
        }else if(getX() >= maze.getWidth()){
            this.setX(0);

        }

        //play soundeffects
        Media sound = new Media(new File("src/Sounds/pacChompBru.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }

    @Override
    public void draw(GraphicsContext graphicsContext, Maze maze) {
        // draw PacMan
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight());
    }

    public void increaseScore(int increment){
        score+=increment;
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

    public int getScore() {
        return score;
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
