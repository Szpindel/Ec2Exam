package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.awt.*;


public abstract class GameObject {
    private int X;
    private int Y;
    protected Point initPosition;

    public GameObject(Point position){
        this.initPosition = position;
        X = position.x;
        Y = position.y;
        System.out.println(X + " " + Y);
    }

    //should this include color?
    abstract void draw(GraphicsContext graphicsContext);

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

    protected void moveUp(){
        Y--;
    }

    protected void moveDown(){
        Y++;
    }

    protected void moveLeft(){
        X--;
    }

    protected void moveRight(){
        X++;
    }
}
