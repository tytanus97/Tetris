package com.mygdx.game.entity.board;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.entity.tetro.Tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private List<Vector2> blocks;
    private int width;
    private int height;
    private int[][] board;

    public Board() {

        this.width = TetrisGame.GAME_WIDTH / TetrisGame.BLOCK_DIV;
        this.height = TetrisGame.GAME_HEIGHT / TetrisGame.BLOCK_DIV;
        this.board = new int[height][width];
        this.blocks = new ArrayList<Vector2>();
        
    }

    public void printBoard() {
        System.out.println("-------------------------------");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------");
    }

    public void clearBoard() {
        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], 0);
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setTetroToBoard(Tetromino tetromino) {

        this.blocks.add(new Vector2().set(tetromino.getPivot()));

    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLUE);

        for(Vector2 block:blocks) {
            shapeRenderer.rect(block.x*TetrisGame.BLOCK_DIV,block.y*TetrisGame.BLOCK_DIV,50,50);
        }
    }
}

