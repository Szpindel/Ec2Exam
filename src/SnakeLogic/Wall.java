package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by brunofloerke on 15/06/2017.
 */
public class Wall extends Field {

    @Override
    boolean isWalkable() {
        return false;
    }

    public Wall(int x, int y, double width, double height){
        super(x,y,width,height);
        color = Color.BLUE;
    }

}
