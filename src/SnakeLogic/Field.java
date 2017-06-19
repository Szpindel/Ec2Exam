package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by brunofloerke on 15/06/2017.
 */



public abstract class Field {
    int x, y;
    double width, height;
    public Color color;

    public Field(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    abstract boolean isWalkable();

    public void draw(GraphicsContext g){
        g.setFill(color);
        g.fillRect(x*width, y*height, width, height);

    };

    @Override
    public String toString(){
        return x + "_" + y;
    }
}
