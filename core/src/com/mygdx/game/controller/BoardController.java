package com.mygdx.game.controller;

import com.mygdx.game.entity.board.Board;
import com.mygdx.game.entity.tetro.Block;
import com.mygdx.game.entity.tetro.Tetromino;

public class BoardController {
    public BoardController() {

    }

    public Board syncBoard(Tetromino tetromino, Board board){

        board.clearBoard();
        try {
            for (Block block : tetromino.getBlockList()) {
                board.getBoard()[(int) (board.getHeight()-block.getPos().y)][(int) block.getPos().x] = 1;
            }
            board.getBoard()[(int)(board.getHeight()-tetromino.getPivot().y)][(int)(tetromino.getPivot().x)] = 1;
        }catch(IndexOutOfBoundsException e) {

        }
        return board;
    }
}
