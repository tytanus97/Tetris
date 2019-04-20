package com.mygdx.game.entity.board;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.entity.tetro.Block;
import com.mygdx.game.entity.tetro.Tetromino;

import java.util.*;

public class Board {

    private List<Block> blocks;
    private int width;
    private int height;
    private int[][] board;

    public Board() {

        this.width = TetrisGame.GAME_WIDTH / TetrisGame.BLOCK_DIV;
        this.height = TetrisGame.GAME_HEIGHT / TetrisGame.BLOCK_DIV;
        this.board = new int[height][width];
        this.blocks = new ArrayList<Block>();

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

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void setTetroToBoard(Tetromino tetromino) {

        //setting rest tetromino blocks to block list
        for(Block block:tetromino.getBlockList()) {
            this.blocks.add(new Block(new Vector2(block.getPos().x,block.getPos().y),tetromino.getColor()));
        }

    }

    public void draw(ShapeRenderer shapeRenderer) {

        for(Block block:blocks) {
            shapeRenderer.setColor(block.getColor());
            shapeRenderer.rect(block.getPos().x*TetrisGame.BLOCK_DIV,block.getPos().y*TetrisGame.BLOCK_DIV
                    ,TetrisGame.BLOCK_SIZE,TetrisGame.BLOCK_SIZE);
        }
    }


    public void scoreBoard() {

        Collections.sort(this.blocks);

        List<Integer> levelsToRemove = new ArrayList<Integer>();

        int counter = 0;
        int level = 0;
        for (Block block : this.blocks) {

            if (block.getPos().y == level) {
                counter++;
            }
            else if(counter == 10) {
                levelsToRemove.add(level);
                counter = 1;
                level++;
            }
            else {
                counter = 1;
                level++;
            }

        }
        System.out.println("levels to remove" + levelsToRemove.toString());
        removeBlocks(levelsToRemove);

    }

    private void removeBlocks(List<Integer> levelsToRemove) {


        List<Block> blockToRemove = new ArrayList<Block>();

        for(int i:levelsToRemove) {
            for(Block block :this.blocks) {
                if(block.getPos().y == i) {
                    blockToRemove.add(block);
                    }
                }
            }
        this.blocks.removeAll(blockToRemove);
        }
    }


