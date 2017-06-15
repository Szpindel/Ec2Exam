package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by brunofloerke on 15/06/2017.
 */
public class Path extends Field {
    boolean hasSmallPill;
    boolean hasBigPill;
    boolean isGhostSpawn;

    public Path(int x, int y, double width, double height) {
        super(x, y, width, height);
        color = Color.BLACK;
    }

    void createSmallPill(){
        hasSmallPill = true;
        hasBigPill = false;
    }

    void createBigPill(){
        hasBigPill = true;
        hasSmallPill = false;
    }

    void makeGhostSpawnPath(){
        isGhostSpawn = true;
        hasSmallPill = false;
        hasBigPill = false;
    }

    @Override
    boolean isWalkable() {
        return true;
    }


}
