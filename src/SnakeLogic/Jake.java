package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.Random;

/**
 * Created by Ebbe Vang on 21-02-2017.
 */
public class Jake implements GameObject{

    private int X;
    private int Y;
    Random random = new Random();
    private SceneInfo sceneInfo;
    private JakeTalePiece taleFirst;
    private JakeTalePiece taleLast;

    public Jake(Point position)
    {
        this.sceneInfo = sceneInfo;
        X = position.x;
        Y = position.y;
    }

    @Override
    public void update(KeyCode keyPressed) {

        if (taleFirst != null) {
            taleFirst.update(keyPressed);
            taleFirst.setX(X);
            taleFirst.setY(Y);

        }

        switch (keyPressed)
        {
            case DOWN:
                this.setY(this.getY() + 1);
                break;
            case LEFT:
                this.setX(this.getX() - 1);
                break;
            case RIGHT:
                this.setX(this.getX() + 1);
                break;
            case UP:
                this.setY(this.getY() - 1);
                break;
        }
    }

    @Override
    public void draw(GraphicsContext graphicsContext, SceneInfo sceneInfo) {
        // draw Jake
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRoundRect(this.getX() * sceneInfo.getFieldWidth(), this.getY() * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight(), 3, 3);

        //draw tale
        if (taleFirst != null) {
            taleFirst.draw(graphicsContext, sceneInfo);
        }
    }

    public int getX() {
        return X;
    }

    public void setX(int x) { X = x; }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void addToTale() {
        JakeTalePiece jtp = new JakeTalePiece();
        if (taleFirst == null)
        {
            taleFirst = jtp;
            taleLast = jtp;
        }
        else
        {
            taleLast.setNext(jtp);
            taleLast = jtp;
        }

    }
}
