package PacLogic;

import PacGUI.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.awt.*;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */

public class PacMan extends GameObject {

    private int score;
    private boolean hasBegun = false;
    private int superPowerTimer = 0;
    private int numberOfLives = 3;
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

        // Update power mode
        if (isSuperPowered()) {
            setSuperPowerTimer(getSuperPowerTimer() - 1);
        }
        if (getSuperPowerTimer() == 0) {
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
        setSuperPowerTimer(25);
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
        numberOfLives = getNumberOfLives() - 1;
        if (getNumberOfLives() < 0) {
            System.out.println("GAME OVER");
            System.exit(0);
        } else {
            super.resetToSpawn();
        }
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public int getSuperPowerTimer() {
        return superPowerTimer;
    }

    public void setSuperPowerTimer(int superPowerTimer) {
        this.superPowerTimer = superPowerTimer;
    }
}
