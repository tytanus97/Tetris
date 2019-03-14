package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.board.Board;
import com.mygdx.game.entity.tetro.Tetromino;

public class BoardController {
    public BoardController() {

    }

    public Board syncBoard(Tetromino tetromino, Board board){

        board.clearBoard();
        try {
            /*for (Vector2 body : tetromino.getBodyList()) {
                board.getBoard()[(int) (board.getHeight()-body.y)][(int) body.x] = 1;
            }*/
            board.getBoard()[(int)(board.getHeight()-tetromino.getPivot().y)][(int)(tetromino.getPivot().x)] = 1;
            board.getBoard()[(int) (board.getHeight()-tetromino.getPivot().y)][(int) tetromino.getPivot().x] = 1;
        }catch(IndexOutOfBoundsException e) {

        }
        return board;
    }
}
