package com.mygdx.game.entity.tetro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LTetro extends Tetromino{

    public LTetro() {
        this.color =  Color.ORANGE;
        init();
    }

    @Override
    protected void init() {
        blockList = new ArrayList<Block>();
        pivot = new Vector2(5,19);
        addBlocks();

    }
    @Override
    protected void rotate() {

    }

    @Override
    public void realocate(Vector2 vector2) {

        pivot.set(vector2);

        blockList.clear();
        addBlocks();

    }

    private void addBlocks() {
        blockList.add(new Block(new Vector2().set(pivot.x,pivot.y),color));
        blockList.add(new Block(new Vector2().set(pivot.x,pivot.y+1),color));
        blockList.add(new Block(new Vector2().set(pivot.x,pivot.y+2),color));
        blockList.add(new Block(new Vector2().set(pivot.x+1,pivot.y),color));
    }
}
