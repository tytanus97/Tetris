package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.tetro.Tetromino;

public class MoveController {

    public MoveController() {}

    public void move(Tetromino tetromino,int direction) {

       /* for(Vector2 body:tetromino.getBodyList()) {
            body.x+=direction;
        }*/
        tetromino.getPivot().x+=direction;

    }
    public Tetromino moveDown(Tetromino tetromino) {

        /*for(Vector2 body:tetromino.getBodyList()) {
            body.y -= 1;
        }*/
        tetromino.getPivot().y -= 1;
        return tetromino;
    }
}
