package com.mygdx.game.controller;

import com.mygdx.game.entity.tetro.*;

public class TetrominoController {

    private Tetromino randTetro;
    private int randomNumber;

    public TetrominoController() {

    }

    public Tetromino getRandomTetromino() {

        randomNumber = (int)(Math.random()*7)+1;

        switch(randomNumber) {
            case 1:randTetro = new ITetro();break;
            case 2:randTetro = new JTetro();break;
            case 3:randTetro = new LTetro();break;
            case 4:randTetro = new ZTetro();break;
            case 5:randTetro = new STetro();break;
            case 6:randTetro = new OTetro();break;
            case 7:randTetro = new TTetro();break;
            default: {} break;
        }

        return randTetro;

    }

    public Tetromino getRandTetro() {
        return randTetro;
    }

    public void setRandTetro(Tetromino randTetro) {
        this.randTetro = randTetro;
    }
}
