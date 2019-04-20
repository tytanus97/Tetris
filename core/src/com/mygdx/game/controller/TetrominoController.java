package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.tetro.*;

public class TetrominoController {

    private Tetromino randTetro;
    private int prevNumber = 0;
    private Vector2 tmpVector;
    private int[][] leftRotationMatrix = {{0,-1},{1,0}};
    private int[][] rightRotationMatrix = {{0,1},{-1,0}};



    public TetrominoController() {
        tmpVector = new Vector2();
    }

    public Tetromino getRandomTetromino() {

        int randomNumber;
        do{
            randomNumber = (int)(Math.random()*7)+1;
        }while(prevNumber == randomNumber);

        //System.out.println("prev num: "+prevNumber);

        prevNumber = randomNumber;

       // System.out.println("rand num: "+ randomNumber);

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


    public void rotateLeft(Tetromino tetromino) {


        for(Block block:tetromino.getBlockList()) {
            tmpVector.set(tetromino.getPivot().x - block.getPos().x,tetromino.getPivot().y - block.getPos().y);

            tmpVector = transformVector(tmpVector,leftRotationMatrix);
            block.getPos().set(tetromino.getPivot().x+tmpVector.x,tetromino.getPivot().y+tmpVector.y);
        }


    }
    public void rotateRight(Tetromino tetromino) {


        for(Block block:tetromino.getBlockList()) {
            tmpVector.set(tetromino.getPivot().x - block.getPos().x,tetromino.getPivot().y - block.getPos().y);
            tmpVector = transformVector(tmpVector,rightRotationMatrix);
            block.getPos().set(tetromino.getPivot().x+tmpVector.x,tetromino.getPivot().y+tmpVector.y);
        }


    }

    private Vector2 transformVector(Vector2 tmpVector,int[][] transformation) {

        int sum = 0;
        float x = 0,y = 0;
        x = tmpVector.x*transformation[0][0] + tmpVector.y *transformation[0][1];
       // System.out.println("x: "+ x);
        y = tmpVector.x*transformation[1][0] + tmpVector.y *transformation[1][1];
        //System.out.println("y: "+ y);

        tmpVector.set(x,y);
        return tmpVector;
    }
}
