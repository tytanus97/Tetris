package com.mygdx.game.entity.tetro;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

    public class ITetro extends Tetromino {

    public ITetro() {
        init();
    }

    @Override
    protected void init() {
        bodyList = new ArrayList<Vector2>();
        pivot = new Vector2(5,19);
       // bodyList.add(new Vector2(5,18));//new Vector2(pivot.x,pivot.y));
       // bodyList.add(new Vector2(pivot.x,pivot.y+2));
       // bodyList.add(new Vector2(pivot.x,pivot.y-1));

    }
    @Override
    protected void rotate() {

    }

    @Override
    public void realocate(Vector2 vector2) {

        pivot.set(vector2);
        /*for(Vector2 body:bodyList) {
            body.set(vector2);
        }*/
        // bodyList.add(new Vector2(pivot.x,pivot.y+2));
        // bodyList.add(new Vector2(pivot.x,pivot.y-1));

    }

}
