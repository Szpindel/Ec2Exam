package SnakeGUI;

import SnakeLogic.GameObject;
import SnakeLogic.Item;
import SnakeLogic.PacMan;
import SnakeLogic.Maze;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Controller {

    private final int itemSpawnPercentage = 80;
    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private Maze maze;
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private PacMan pacMan;

    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
        //getRandomPosition();
        drawCanvas();
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize()
    {
            maze = new Maze(canvas);

//        pacMan = new PacMan(maze.getRandomPoint());
//        gameObjects.add(pacMan);
//
//        AddItems();
//
        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now)
            {
                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);
                }
            }
        }.start();
    }

    private void AddItems() {
        Point pos1 = maze.getRandomPoint();
        gameObjects.add(new Item(Color.BLUE, pos1.x, pos1.y, pacMan));
        Point pos2 = maze.getRandomPoint();
        gameObjects.add(new Item(Color.LIGHTBLUE, pos2.x,pos2.y, pacMan));
    }

    public void keyPressed(KeyCode keyCode)
    {
        this.keyPressed = keyCode;
    }

    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now)
    {
//
//        for (int i = 0; i <gameObjects.size() ; i++) {
//            gameObjects.get(i).update(keyPressed);
//        }
//
//        if (random.nextInt(100) > itemSpawnPercentage)
//        {
//            Point randomPoint = maze.getRandomPoint();
//            gameObjects.add(new Item(Color.LIGHTBLUE, randomPoint.x,randomPoint.y, pacMan));
//        }

        drawCanvas();

    }


    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // clear canvas
        g.clearRect(0,0, maze.getWidth()* maze.getFieldWidth() , maze.getHeight()* maze.getFieldHeight());

        // Draw maze
        maze.draw(g);

        // Draw pills

        // Draw ghosts

        // Draw pacman

        // draw gameObjects
        for (GameObject item : gameObjects)
        {
            item.draw(g, maze);
        }

    }


}