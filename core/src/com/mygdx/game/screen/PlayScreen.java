package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.entity.ITetro;
import com.mygdx.game.entity.Tetromino;

import java.util.Arrays;



public class PlayScreen extends AbstractScreen{


    private int width;
    private int height;
    private ShapeRenderer shapeRenderer;
    private int[][] board;
    private float timer = 0;

    Tetromino tetromino = new ITetro();




    public PlayScreen(TetrisGame tetrisGame) {
        super(tetrisGame);
        this.width = TetrisGame.GAME_HEIGHT/TetrisGame.BLOCK_DIV;
        this.height = TetrisGame.GAME_WIDTH/TetrisGame.BLOCK_DIV;
        this.board = new int[width][height];
        for(int i=0;i<width;i++) {
            Arrays.fill(board[i], 0);
        }
        shapeRenderer = new ShapeRenderer();

        printBoard();
    }

    private void printBoard() {
        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("");
        }
    }
    @Override
    protected void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();

    }

    private void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        tetromino.draw(shapeRenderer);
        shapeRenderer.setColor(Color.BLACK);
        for(int i=1;i<(TetrisGame.GAME_WIDTH/TetrisGame.BLOCK_DIV);i++)
        {
            shapeRenderer.rectLine(new Vector2(i* TetrisGame.BLOCK_DIV,0),new Vector2(i*TetrisGame.BLOCK_DIV,TetrisGame.GAME_HEIGHT),1);
        }
        for(int i=1;i<(TetrisGame.GAME_HEIGHT/TetrisGame.BLOCK_DIV);i++)
        {
            shapeRenderer.rectLine(new Vector2(0,i*TetrisGame.BLOCK_DIV),new Vector2(TetrisGame.GAME_WIDTH,i*TetrisGame.BLOCK_DIV),1);
        }


        shapeRenderer.line(new Vector2(50,0),new Vector2(50,950));
        shapeRenderer.end();

    }

    private void update(float delta) {

        shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());
        timer +=delta;
        if(timer > 1) {
            tetromino.update(delta);
            timer = 0;
        }
    }



}
