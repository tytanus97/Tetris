package com.mygdx.game.entity;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class ITetro extends Tetromino{



    public ITetro() {

        init();
    }

    @Override
    protected void init() {
        bodyList = new ArrayList<Vector2>();
        pivot = new Vector2(5,19);
        bodyList.add(new Vector2(pivot.x,pivot.y+1));
        bodyList.add(new Vector2(pivot.x,pivot.y+2));
        bodyList.add(new Vector2(pivot.x-1,pivot.y));

    }
    @Override
    protected void rotate() {

    }

    public void update(float dt) {
        pivot.y-=1;
        for(Vector2 vector:bodyList) {
            vector.y-=1;
        }
    }
}
