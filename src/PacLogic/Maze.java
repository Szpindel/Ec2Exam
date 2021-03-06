package PacLogic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */
public class Maze {
    private double fieldHeight = 20;
    private double fieldWidth = 20;
    private int width;
    private int height;
    private Field[][] fields;

    public Maze(Canvas canvas) {
        // Load array and set height
        int[][] array = convertToArray("Maze_0.txt");

        height = array[0].length;
        width = array.length;
        fields = new Field[width][height];

        // Set field dimensions
        canvas.setHeight(height * fieldHeight);
        canvas.setWidth(width * fieldWidth);

        //iterate through array and visually represent elements of maze
        for (int y = 0; y < array[0].length; y++) {
            for (int x = 0; x < array.length; x++) {
                int type = array[x][y];
                switch (type) {
                    case 0:
                        Wall wall = new Wall(x, y, fieldWidth, fieldHeight);
                        fields[x][y] = wall;
                        break;
                    case 1:
                        Path smallPillPath = new Path(x, y, fieldWidth, fieldHeight);
                        smallPillPath.assignSmallPill();
                        fields[x][y] = smallPillPath;
                        break;
                    case 2:
                        Path bigPillPath = new Path(x, y, fieldWidth, fieldHeight);
                        bigPillPath.assignBigPill();
                        fields[x][y] = bigPillPath;
                        break;
                    case 3:
                        Path emptyPath = new Path(x, y, fieldWidth, fieldHeight);
                        emptyPath.assignEmptyPath();
                        fields[x][y] = emptyPath;
                        break;
                    case 4:
                        Path ghostSpawnPath = new Path(x, y, fieldWidth, fieldHeight);
                        ghostSpawnPath.assignGhostSpawnPath();
                        fields[x][y] = ghostSpawnPath;
                        break;
                }
            }
        }
    }

    public void draw(GraphicsContext g) {
        // Draw fields
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fields[x][y].draw(g);
            }
        }
    }

    //read Maze_0.txt
    public static int[][] convertToArray(String fileName) {
        List<List<Integer>> twoDim = new ArrayList<List<Integer>>();
        int height = 0;
        int width = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = reader.readLine();
            while (line != null) {
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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int var = twoDim.get(y).get(x);
                array[x][y] = var;
            }
        }

        return array;
    }


    public boolean checkAvailability(GameObject gameObject, int direction) {
        boolean walkable = false;

        switch (direction) {
            case 0:
                if (fields[gameObject.getX()][gameObject.getY() - 1].isWalkable() && gameObject.getY() - 1 > 0) {
                    walkable = true;
                }
                break;
            case 1:
                if (fields[gameObject.getX() - 1][gameObject.getY()].isWalkable() && gameObject.getX() - 1 > 0) {
                    walkable = true;
                }
                break;
            case 2:
                if (fields[gameObject.getX() + 1][gameObject.getY()].isWalkable() && gameObject.getX() + 1 < width) {
                    walkable = true;
                }
                break;
            case 3:
                if (fields[gameObject.getX()][gameObject.getY() + 1].isWalkable() && gameObject.getY() + 1 < height) {
                    walkable = true;
                }
                break;
            default:
                System.out.println("Error in direction: " + direction);
                break;
        }

        return walkable;
    }

    ArrayList<Field> getPossibleMoves(Field field) {
        ArrayList<Field> possibleMoves = new ArrayList<>();

        //check if UP isWalkable and add to possibleMoves
        if (fields[field.x][field.y - 1].isWalkable() && field.y - 1 > 0) {
            possibleMoves.add(fields[field.x][field.y - 1]);
        }
        //check if Left isWalkable and add to possibleMoves
        if (fields[field.x - 1][field.y].isWalkable() && field.x - 1 > 0) {
            possibleMoves.add(fields[field.x - 1][field.y]);
        }
        //check if Right isWalkable and add to possibleMoves
        if (fields[field.x + 1][field.y].isWalkable() && field.x + 1 < width) {
            possibleMoves.add(fields[field.x + 1][field.y]);
        }
        //check if Down isWalkable and add to possibleMoves
        if (fields[field.x][field.y + 1].isWalkable() && field.y + 1 < height) {
            possibleMoves.add(fields[field.x][field.y + 1]);
        }

        return possibleMoves;
    }

    public Field getField(GameObject gameObject){
        return fields[gameObject.getX()][gameObject.getY()];
    }

    public void update(PacMan pacman) {
        //Check for pills
        if (fields[pacman.getX()][pacman.getY()] instanceof Path) {
            Path path = (Path) fields[pacman.getX()][pacman.getY()];
            if (path.hasSmallPill) {
                pacman.increaseScore(10);
                path.removePill();
            } else if (path.hasBigPill) {
                pacman.increaseScore(50);
                path.removePill();
                // SET SUPER MODE FOR PACMAN
                pacman.enableSuperPower();
                //increase pacSpeed()
            }
        }
        ;
    }

    public double getFieldHeight() {
        return fieldHeight;
    }

    public double getFieldWidth() {
        return fieldWidth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


