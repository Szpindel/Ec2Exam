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
    private Jake jake;

    public Item(Color color, int x, int y, Jake jake) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.jake = jake;
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

        if (x == jake.getX() && y == jake.getY())
        {
            color = Color.RED;
            jake.addToTale();
        }

    }

    @Override
    public void draw(GraphicsContext g, SceneInfo sceneInfo) {
        g.setFill(this.getColor());

        g.fillRoundRect(this.getX() * sceneInfo.getFieldWidth(), this.getY() * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight(), 5,5);
    }
}
