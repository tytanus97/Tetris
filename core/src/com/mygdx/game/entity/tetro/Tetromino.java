package com.mygdx.game.entity.tetro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;

import java.util.List;


public abstract class Tetromino {

    protected Color color;
    protected List<Block> blockList;
    protected Vector2 pivot;
    protected int blockSize;

    public Tetromino() {
        this.blockSize = 50;
        init();
    }


    protected abstract void init();
    protected abstract void rotate();

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(this.color);
        for(Block block:blockList) {
            shapeRenderer.rect(block.getPos().x * TetrisGame.BLOCK_DIV,block.getPos().y * TetrisGame.BLOCK_DIV,blockSize,blockSize);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setBodyList(List<Block> blockList) {
        this.blockList = blockList;
    }

    public Vector2 getPivot() {
        return pivot;
    }

    public void setPivot(Vector2 pivot) {
        this.pivot = pivot;
    }

    public abstract void realocate(Vector2 vector2);
}


