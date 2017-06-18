package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Created by Ebbe Vang on 23-01-2017.
 */
public interface GameObject {

    void update(KeyCode keyPressed, Maze maze);


    //should this include color?
    void draw(GraphicsContext graphicsContext, Maze maze);
}
