package SnakeLogic;

import SnakeGUI.Controller;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;


public abstract class GameObject {
    private int X;
    private int Y;

    public GameObject(Point position){
        setX(position.x);
        setY(position.y);
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
        if(getX() <= 0){
            setX(Controller.maze.getWidth());
        }

    }

    protected void moveRight(){
        setX(getX() + 1);
        if(getX() >= Controller.maze.getWidth()){
            setX(0);
        }
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
