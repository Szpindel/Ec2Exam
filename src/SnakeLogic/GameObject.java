package SnakeLogic;

import SnakeGUI.Controller;
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
    }
//
//    //should this include color?
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
        if(X <= 0){
            X = Controller.maze.getWidth();
        }

    }

    protected void moveRight(){
        X++;

        if(X >= Controller.maze.getWidth()){
            X = 0;
        }
    }
}
