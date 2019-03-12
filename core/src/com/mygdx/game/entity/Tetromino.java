package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;

import java.util.List;


public abstract class Tetromino {

    protected Color color;
    protected List<Vector2> bodyList;
    protected Vector2 pivot;
    protected int blockSize;

    public Tetromino() {
        this.blockSize = 50;
        init();
    }

    protected abstract void init();
    protected abstract void rotate();

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(pivot.x * TetrisGame.BLOCK_DIV,pivot.y * TetrisGame.BLOCK_DIV,blockSize,blockSize);
        shapeRenderer.setColor(Color.RED);
        for(Vector2 vector:bodyList) {
            shapeRenderer.rect(vector.x * TetrisGame.BLOCK_DIV,vector.y * TetrisGame.BLOCK_DIV,blockSize,blockSize);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Vector2> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<Vector2> bodyList) {
        this.bodyList = bodyList;
    }

    public Vector2 getPivot() {
        return pivot;
    }

    public void setPivot(Vector2 pivot) {
        this.pivot = pivot;
    }


}


