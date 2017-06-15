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
    boolean isEmptyPath;

    @Override
    boolean isWalkable() {
        return true;
    }


    public Path(int x, int y, double width, double height) {
        super(x, y, width, height);
        color = Color.BLACK;
    }

    void createSmallPill() {
        isGhostSpawn = false;
        hasSmallPill = true;
        hasBigPill = false;
        isEmptyPath = false;
    }


    void createBigPill() {
        isGhostSpawn = false;
        hasBigPill = true;
        hasSmallPill = false;
        isEmptyPath = false;
    }

    void createEmptyPath() {
        isGhostSpawn = false;
        hasSmallPill = false;
        hasBigPill = false;
        isEmptyPath = true;
    }

    void createGhostSpawnPath() {
        isGhostSpawn = true;
        hasSmallPill = false;
        hasBigPill = false;
        isEmptyPath = false;

    }

    @Override
    public void draw(GraphicsContext g){
        if(hasSmallPill){
            drawSmallPill(g);
        }else if (hasBigPill){
            drawBigPill(g);
        }
    }

    void drawSmallPill(GraphicsContext g){
        g.setFill(Color.WHITE);
        g.fillRect(x*width, y*height, width/4, height/4);
    }

    void drawBigPill(GraphicsContext g){
        g.setFill(Color.WHITE);
        g.fillOval(x*width, y*height, width/2, height/2);
    }
}
