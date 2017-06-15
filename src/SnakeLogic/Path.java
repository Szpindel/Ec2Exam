package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by brunofloerke on 15/06/2017.
 */
public class Path extends Field {
    public Path(int x, int y, double width, double height) {
        super(x, y, width, height);
        color = Color.BLACK;
    }

    @Override
    boolean isWalkable() {
        return true;
    }


}
