package SnakeLogic;

import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.util.Random;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */
public class Maze {
    private double fieldHeight;
    private double fieldWidth;
    private int width = 30;
    private int height = 20;
    private Random random = new Random();

    public Maze(Canvas canvas)
    {
        fieldHeight = canvas.getHeight() / height;
        fieldWidth =    canvas.getWidth() / width;
    }

    public double getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(double fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public double getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(double fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getRandomPoint() {
        return new Point(random.nextInt(width), random.nextInt(height));
    }
}


