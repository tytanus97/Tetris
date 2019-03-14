package com.mygdx.game.controller;

import com.mygdx.game.entity.tetro.Block;
import com.mygdx.game.entity.tetro.Tetromino;

public class MoveController {

    public MoveController() {}

    public void move(Tetromino tetromino,int direction) {

        for(Block block:tetromino.getBlockList()) {
            block.getPos().x+=direction;
        }
        tetromino.getPivot().x+=direction;

    }
    public Tetromino moveDown(Tetromino tetromino) {

        for(Block block:tetromino.getBlockList()) {
            block.getPos().y -= 1;
        }
        tetromino.getPivot().y -= 1;
        return tetromino;
    }
}
