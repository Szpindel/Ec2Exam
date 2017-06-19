package SnakeGUI;

import SnakeLogic.*;
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
    Canvas canvas;

    public static Maze maze;
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    //ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    ArrayList<Ghost> ghosts = new ArrayList<>();
    private PacMan pacMan;

    private Point pacSpawnPos = new Point (1,1);
    private Point pinkGhostSpawnPos = new Point (15,15);
    private Point blueGhostSpawnPos = new Point (14,15);
    private Point redGhostSpawnPos = new Point (14,14);
    private Point orangeGhostSpawnPos = new Point (15,14);


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

            pacMan = new PacMan(pacSpawnPos);
      //      gameObjects.add(pacMan);
            ghosts.add(new PinkGhost(getPinkGhostSpawnPos()));
            ghosts.add(new RedGhost(getRedGhostSpawnPos()));
            ghosts.add(new OrangeGhost(getOrangeGhostSpawnPos()));
            ghosts.add(new BlueGhost(getBlueGhostSpawnPos()));
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
//    LEFTOVER CODE
//    private void AddItems() {
//        Point pos1 = maze.getRandomPoint();
//        gameObjects.add(new Item(Color.BLUE, pos1.x, pos1.y, pacMan));
//        Point pos2 = maze.getRandomPoint();
//        gameObjects.add(new Item(Color.LIGHTBLUE, pos2.x,pos2.y, pacMan));
//    }

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
        pacMan.update(keyPressed);


       maze.update(pacMan);

        for (Ghost ghost : ghosts){
            ghost.update(pacMan);

        }

        labelStatus.setText(pacMan.getScore()+"");

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

        // Draw ghosts
        for (Ghost ghost : ghosts){
            ghost.draw(g);
        }
        // Draw pacman
        pacMan.draw(g);

//        // draw gameObjects
//        for (GameObject item : gameObjects)
//        {
//            item.draw(g, maze);
//        }
    }


    public Point getPacSpawnPos() {
        return pacSpawnPos;
    }

    public void setPacSpawnPos(Point pacSpawnPos) {
        this.pacSpawnPos = pacSpawnPos;
    }

    public Point getPinkGhostSpawnPos() {
        return pinkGhostSpawnPos;
    }

    public void setPinkGhostSpawnPos(Point pinkGhostSpawnPos) {
        this.pinkGhostSpawnPos = pinkGhostSpawnPos;
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