package PacLogic;

import javafx.scene.paint.Color;

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
        color = Color.DARKBLUE;
    }

}
