package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Created by Ebbe Vang on 19-01-2017.
 */
public class Item implements GameObject{
    private Color color;
    private int x;
    private int y;
    private PacMan pacMan;

    public Item(Color color, int x, int y, PacMan pacMan) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.pacMan = pacMan;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void update(KeyCode keypressed) {

        if (x == pacMan.getX() && y == pacMan.getY())
        {
            color = Color.RED;
            pacMan.addToTale();
        }

    }

    @Override
    public void draw(GraphicsContext g, Maze maze) {
        g.setFill(this.getColor());

        g.fillRoundRect(this.getX() * maze.getFieldWidth(), this.getY() * maze.getFieldHeight(), maze.getFieldWidth(), maze.getFieldHeight(), 5,5);
    }
}
