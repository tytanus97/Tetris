package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.controller.BoardController;
import com.mygdx.game.controller.MoveController;
import com.mygdx.game.entity.Board;
import com.mygdx.game.entity.ITetro;
import com.mygdx.game.entity.Tetromino;

import java.util.Arrays;



public class PlayScreen extends AbstractScreen{



    private ShapeRenderer shapeRenderer;
    private float timer = 0;
    private MoveController moveController;
    private BoardController boardController;
    private Board board;

    Tetromino tetromino = new ITetro();




    public PlayScreen(TetrisGame tetrisGame) {
        super(tetrisGame);

        this.board.clearBoard();
        this.board.printBoard();
    }


    @Override
    protected void init() {
        shapeRenderer = new ShapeRenderer();
        moveController = new MoveController();
        boardController = new BoardController();
        board = new Board();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkInput();
        draw();

    }

    private void checkInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) ) {
            moveController.move(tetromino,-1);

        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            moveController.move(tetromino,1);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            tetromino = moveController.moveDown(tetromino);
        }
    }

    private void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        tetromino.draw(shapeRenderer);
        drawLines(shapeRenderer);
        shapeRenderer.end();

    }

    private void drawLines(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(Color.BLACK);
        for(int i=1;i<(TetrisGame.GAME_WIDTH/TetrisGame.BLOCK_DIV);i++)
        {
            shapeRenderer.rectLine(new Vector2(i* TetrisGame.BLOCK_DIV,0),new Vector2(i*TetrisGame.BLOCK_DIV,TetrisGame.GAME_HEIGHT),1);
        }
        for(int i=1;i<(TetrisGame.GAME_HEIGHT/TetrisGame.BLOCK_DIV);i++)
        {
            shapeRenderer.rectLine(new Vector2(0,i*TetrisGame.BLOCK_DIV),new Vector2(TetrisGame.GAME_WIDTH,i*TetrisGame.BLOCK_DIV),1);
        }
    }

    private void update(float delta) {

        shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());
        this.board.setBoard(boardController.syncBoard(tetromino,this.board));
        timer +=delta;
        if(timer > 0.5) {
            this.board.printBoard();
            moveController.moveDown(tetromino);
            timer = 0;

        }
    }

    }
    
