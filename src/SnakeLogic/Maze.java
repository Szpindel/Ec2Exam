package SnakeLogic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.Random;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */
public class Maze {
    private double fieldHeight;
    private double fieldWidth;
    private int width = 20;
    private int height = 20;
    private Random random = new Random();
    private Field[][] fields = new Field[width][height];

    public Maze(Canvas canvas)
    {
        fieldHeight = canvas.getHeight() / height;
        fieldWidth =  canvas.getWidth() / width;

        // Populate fields
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(Math.random() > 0.5) {
                    fields[x][y] = new Path(x,y,fieldWidth,fieldHeight);
                    System.out.println("path");
                }else{
                    fields[x][y] = new Wall(x,y,fieldWidth,fieldHeight);
                    System.out.println("wall");
                }
            }
        }
    }




    public void draw(GraphicsContext g) {
        // Draw fields
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                fields[x][y].draw(g);
            }
        }
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


