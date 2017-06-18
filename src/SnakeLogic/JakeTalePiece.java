package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Created by Ebbe Vang on 01-03-2017.
 */
public class JakeTalePiece implements GameObject{

    //DELETE THIS CLASS?

    private int x;
    private int y;
    private JakeTalePiece next;
    @Override
    public void update(KeyCode keyPressed, Maze maze) {
        if (next != null)
        {
            //update next position
            getNext().update(keyPressed, maze);

            getNext().setX(x);
            getNext().setY(y);
          }
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Maze maze) {
        // draw it self
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRoundRect(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight(), 3, 3);
        // draw next
        if (next != null){
            next.draw(graphicsContext, maze);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JakeTalePiece getNext() {
        return next;
    }

    public void setNext(JakeTalePiece next) {
        this.next = next;
    }
}
