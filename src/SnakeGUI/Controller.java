package SnakeGUI;

import SnakeLogic.GameObject;
import SnakeLogic.Item;
import SnakeLogic.Jake;
import SnakeLogic.SceneInfo;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;

public class Controller {

    private final int itemSpawnPercentage = 80;
    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private SceneInfo sceneInfo;
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private Jake jake;

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
        sceneInfo = new SceneInfo(canvas);

        jake = new Jake(sceneInfo.getRandomPoint());
        gameObjects.add(jake);

        AddItems();

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
        Point pos1 = sceneInfo.getRandomPoint();
        gameObjects.add(new Item(Color.BLUE, pos1.x, pos1.y, jake));
        Point pos2 = sceneInfo.getRandomPoint();
        gameObjects.add(new Item(Color.LIGHTBLUE, pos2.x,pos2.y, jake));
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

        for (int i = 0; i <gameObjects.size() ; i++) {
            gameObjects.get(i).update(keyPressed);
        }

        if (random.nextInt(100) > itemSpawnPercentage)
        {
            Point randomPoint = sceneInfo.getRandomPoint();
            gameObjects.add(new Item(Color.LIGHTBLUE, randomPoint.x,randomPoint.y, jake));
        }

        drawCanvas();

    }


    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // clear canvas
        g.clearRect(0,0,sceneInfo.getWidth()*sceneInfo.getFieldWidth() ,sceneInfo.getHeight()*sceneInfo.getFieldHeight());

        // draw gameObjects
        for (GameObject item : gameObjects)
        {
            item.draw(g, sceneInfo);
        }

    }


}