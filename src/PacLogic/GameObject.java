package PacLogic;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;


public abstract class GameObject {
    private int X;
    private int Y;
    private Point initPosition;


    public GameObject(Point position){
        setX(position.x);
        setY(position.y);
        initPosition = position;
    }
//

    abstract void draw(GraphicsContext graphicsContext);

    protected void moveUp(){
        setY(getY() - 1);
    }

    protected void moveDown(){
        setY(getY() + 1);
    }

    protected void moveLeft(){
        setX(getX() - 1);
    }

    protected void moveRight(){
        setX(getX() + 1);
    }

    public void resetToSpawn() {
        // Potential error when casting
        this.setY((int) getInitPosition().getY());
        this.setX((int) getInitPosition().getX());
    }


    public Point getInitPosition() {
        return initPosition;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
