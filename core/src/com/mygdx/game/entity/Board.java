package com.mygdx.game.entity;


import com.mygdx.game.TetrisGame;

import java.util.Arrays;

public class Board {

    private int width;
    private int height;
    private int[][] board;

    public Board() {

        this.width = TetrisGame.GAME_HEIGHT / TetrisGame.BLOCK_DIV;
        this.height = TetrisGame.GAME_WIDTH / TetrisGame.BLOCK_DIV;
        this.board = new int[width][height];
    }

    public void printBoard() {
        System.out.println("-------------------------------");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------");
    }

    public void clearBoard() {
        for (int i = 0; i < width; i++) {
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
}

