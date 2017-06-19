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
    //if toggled when superPowered ++score, killGhost(); if toggled when regular ol' Pac display GAME OVER and System.Exit(0)

    @Override
    boolean isWalkable() {
        return true;
    }


    public Path(int x, int y, double width, double height) {
        super(x, y, width, height);
        color = Color.BLACK;
    }

    void assignSmallPill() {
        isGhostSpawn = false;
        hasSmallPill = true;
        hasBigPill = false;
        isEmptyPath = false;
    }


    void assignBigPill() {
        isGhostSpawn = false;
        hasBigPill = true;
        hasSmallPill = false;
        isEmptyPath = false;
    }

    void assignEmptyPath() {
        isGhostSpawn = false;
        hasSmallPill = false;
        hasBigPill = false;
        isEmptyPath = true;
    }

    void assignGhostSpawnPath() {
        isGhostSpawn = true;
        hasSmallPill = false;
        hasBigPill = false;
        isEmptyPath = false;
    }

    @Override
    public void draw(GraphicsContext g){
        g.setFill(color);
        super.draw(g);

        if(hasSmallPill){
            drawSmallPill(g);
        }else if (hasBigPill){
            drawBigPill(g);
        }
    }

    void drawSmallPill(GraphicsContext g){
        double radius = width/5;
        double offSet = width/2-radius/2;
        g.setFill(Color.WHITE);
        g.fillOval(x*width+offSet, y*height+offSet, radius, radius);
    }

    void drawBigPill(GraphicsContext g){
        double radius = width/2.3;
        double offSet = width/2-radius/2;
        g.setFill(Color.WHITE);
        g.fillOval(x*width+offSet, y*height+offSet, radius, radius);
    }

    public void removePill() {
        hasBigPill = hasSmallPill = false;
    }
}
