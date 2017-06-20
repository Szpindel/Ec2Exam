package PacLogic;

import PacGUI.Controller;
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
    int numberOfMovesChecked = 0;


    private int score;


    public Ghost(Point position) {
        super(position);
    }

    public void update(PacMan pacMan) {

        // Change behaviour
        if (pacMan.isHasBegun() == true) {
            if (pacMan.isSuperPowered() == false) {
                chase(pacMan);
            } else {
                flee(pacMan);
            }
        } else {
            // random
            beRandom();
        }

        // Check kill conditions
        checkKillConditions(pacMan);
    }


    protected void beRandom() {
        int random = (int) (Math.random() * 4);

        if (Controller.maze.checkAvailability(this, random)) {
            // Move
            switch (random) {
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
        } else {
            // try again
            beRandom();
        }


    }

    abstract protected void chase(PacMan pacMan);

    abstract protected void flee(PacMan pacMan);


    private void checkKillConditions(PacMan pacMan) {
        //compare ghost position with pac position
        if (this.getX() == pacMan.getX() && this.getY() == pacMan.getY()) {
            //check for superPoweredness
            if (pacMan.isSuperPowered()) {
                resetToSpawn();
                System.out.println("Ghost respawns.");
                pacMan.increaseScore(200);
            } else {
                pacMan.removeLife();
                System.out.println("Pacman dies.");
            }
        }
    }

    protected void move(Field nextPath) {
        this.setX(nextPath.x);
        this.setY(nextPath.y);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        // draw Ghost
        graphicsContext.setFill(color);
        graphicsContext.fillOval(this.getX() * Controller.maze.getFieldWidth(), this.getY() * Controller.maze.getFieldHeight(), Controller.maze.getFieldWidth(), Controller.maze.getFieldHeight());
        graphicsContext.fillRect(this.getX() * Controller.maze.getFieldWidth(), (this.getY() * Controller.maze.getFieldHeight()) + Controller.maze.getFieldHeight() / 2, Controller.maze.getFieldWidth(), (Controller.maze.getFieldHeight()) / 2);
    }
}