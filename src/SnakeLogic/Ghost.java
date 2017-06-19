package SnakeLogic;
import SnakeGUI.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.HashSet;

/**
 * Created by brunofloerke on 16/06/2017.
 */
public abstract class Ghost extends GameObject {
    protected Color color;
    HashSet<String> seenFields = new HashSet<>();

    private int score;
    // Random random = new Random();

    // private JakeTalePiece taleFirst;
    //   private JakeTalePiece taleLast;

    public Ghost(Point position) {
        super(position);
    }

    public void update(PacMan pacMan) {
//        beRandom();

        // Change behaviour
        changeBehaviour(pacMan);

        // Print info
        if (pacMan.isSuperPowered()) {
            // FLEE
        }

        // Check kill conditions
        checkKillConditions(pacMan);
    }

    protected void changeBehaviour(PacMan pacMan) {
        // TEMP
//        beRandom();

        chase(pacMan);
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

    abstract protected void chase(PacMan pacMan);


    private void checkKillConditions(PacMan pacMan) {
        if (this.getX() == pacMan.getX() && this.getY() == pacMan.getY()) {
            if (pacMan.isSuperPowered()) {
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
        this.setY((int) super.getInitPosition().getY());
        this.setX((int) super.getInitPosition().getX());
    }

}