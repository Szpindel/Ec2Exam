package PacGUI;

import PacLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.*;

public class Controller {

    private final int itemSpawnPercentage = 80;
    @FXML
    Label labelStatus;
    @FXML
    Label labelField;
    @FXML
    Canvas canvas;

    public static Maze maze;
    private float refreshRate = 300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    ArrayList<Ghost> ghosts = new ArrayList<>();
    private PacMan pacMan;

    private Point pacSpawnPos = new Point(14, 23);
    private Point pinkGhostSpawnPos = new Point(15, 15);
    private Point blueGhostSpawnPos = new Point(14, 15);
    private Point redGhostSpawnPos = new Point(14, 14);
    private Point orangeGhostSpawnPos = new Point(15, 14);


    public void btnStartAction(ActionEvent event) {
        drawCanvas();
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize() {
        maze = new Maze(canvas);

        pacMan = new PacMan(pacSpawnPos);
        ghosts.add(new PinkGhost(getPinkGhostSpawnPos()));
        ghosts.add(new RedGhost(getRedGhostSpawnPos()));
        ghosts.add(new OrangeGhost(getOrangeGhostSpawnPos()));
        ghosts.add(new BlueGhost(getBlueGhostSpawnPos()));

        // Start and control game loop
        new AnimationTimer() {
            long lastUpdate;

            public void handle(long now) {
                if (now > lastUpdate + refreshRate * 1000000) {
                    lastUpdate = now;
                    update(now);
                }
            }
        }.start();
    }


    public void keyPressed(KeyCode keyCode) {
        this.keyPressed = keyCode;
    }

    /**
     * Game loop - executed continously during the game
     *
     * @param now game time in nano seconds
     */
    private void update(long now) {
        pacMan.update(keyPressed);


        maze.update(pacMan);

        for (Ghost ghost : ghosts) {
            ghost.update(pacMan);

        }

        labelStatus.setText("Remaining lives: " + pacMan.getNumberOfLives() + "   Score: " + pacMan.getScore() + "   Superpowered: " + pacMan.getSuperPowerTimer());
        drawCanvas();

    }


    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // clear canvas
        g.clearRect(0, 0, maze.getWidth() * maze.getFieldWidth(), maze.getHeight() * maze.getFieldHeight());

        // Draw maze
        maze.draw(g);

        // Draw ghosts
        for (Ghost ghost : ghosts) {
            ghost.draw(g);
        }
        // Draw pacman
        pacMan.draw(g);

    }

    public Point getPinkGhostSpawnPos() {
        return pinkGhostSpawnPos;
    }

    public Point getBlueGhostSpawnPos() {
        return blueGhostSpawnPos;
    }

    public Point getRedGhostSpawnPos() {
        return redGhostSpawnPos;
    }

    public Point getOrangeGhostSpawnPos() {
        return orangeGhostSpawnPos;
    }
}