package SnakeLogic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */
public class Maze {
    private double fieldHeight;
    private double fieldWidth;
    private int width = 20;
    private int height = 20;
    private Random random = new Random();
    private Field[][] fields;

    public Maze(Canvas canvas) {
        // Load array and set height
        int[][] array = convertToArray("Maze_0.txt");

        height = array[0].length;
        width = array.length;
        fields = new Field[width][height];

        // Set field dimensions
        fieldHeight = canvas.getHeight() / height;
        fieldWidth =  canvas.getWidth() / width;

        for(int y = 0; y < array[0].length; y++){
            for (int x = 0; x < array.length; x++) {
                int type = array[x][y];
                switch (type){
                    case 0:
                        Wall wall = new Wall(x,y,fieldWidth,fieldHeight);
                        fields[x][y] = wall;
                        break;
                    case 1:
                        Path smallPillPath = new Path(x,y,fieldWidth,fieldHeight);
                        smallPillPath.createSmallPill();
                        fields[x][y] = smallPillPath;
                        break;
                    case 2:
                        Path bigPillPath = new Path(x,y,fieldWidth,fieldHeight);
                        bigPillPath.createBigPill();
                        fields[x][y] = bigPillPath;
                        break;
                    case 3:
                        Path emptyPath = new Path(x,y,fieldWidth,fieldHeight);
                        emptyPath.createEmptyPath();
                        fields[x][y] = emptyPath;
                        break;
                    case 4:
                        Path ghostSpawnPath = new Path(x,y,fieldWidth,fieldHeight);
                        ghostSpawnPath.createGhostSpawnPath();
                        fields[x][y] = ghostSpawnPath;
                        break;
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

    public static int[][] convertToArray(String fileName){
        List<List<Integer>> twoDim = new ArrayList<List<Integer>>();
        int height = 0;
        int width = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = reader.readLine();
            while(line != null) {
                twoDim.add(new ArrayList<Integer>());

                for (char c : line.toCharArray()) {
                    twoDim.get(height).add(Character.getNumericValue(c));
                }
                line = reader.readLine();
                height++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = twoDim.get(0).size();
        height = twoDim.size();

        int[][] array = new int[width][height];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int var = twoDim.get(y).get(x);
                array[x][y] = var;
            }
        }

        return array;
    }


    public boolean checkAvailability(Point point) {
        return fields[point.x][point.y].isWalkable();
    }
}


