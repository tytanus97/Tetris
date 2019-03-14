package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.controller.BoardController;
import com.mygdx.game.controller.MoveController;
import com.mygdx.game.entity.board.Board;
import com.mygdx.game.entity.tetro.ITetro;
import com.mygdx.game.entity.tetro.Tetromino;

public class PlayScreen extends AbstractScreen{

    private ShapeRenderer shapeRenderer;
    private float timer = 0;
    private MoveController moveController;
    private BoardController boardController;
    private Board board;
    private Tetromino tetromino;
    private float tickTime;

    public PlayScreen(TetrisGame tetrisGame) {
        super(tetrisGame);
        tetromino  = new ITetro();
        this.board.clearBoard();
        this.board.printBoard();

        this.board = (boardController.syncBoard(tetromino,this.board));
    }


    @Override
    protected void init() {
        shapeRenderer = new ShapeRenderer();
        moveController = new MoveController();
        boardController = new BoardController();
        board = new Board();
        tickTime = 0.5f;


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        checkInput();
        update(delta);
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
            this.tickTime = 0.02f;
        }
        else this.tickTime = 0.5f;
    }

    private void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        tetromino.draw(shapeRenderer);
        board.draw(shapeRenderer);
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



        this.board = (boardController.syncBoard(tetromino,this.board));
        timer +=delta;
        if(timer > tickTime) {
            checkColisionWithGround();
            this.board.printBoard();
            tetromino = moveController.moveDown(tetromino);
            System.out.println(tetromino.getPivot().y+" "+tetromino.getPivot().x);
            timer = 0f;
        }
    }

    private void checkColisionWithGround() {

        boolean touchedGround = false;

        for(Vector2 body:tetromino.getBodyList()) {
            if(body.y == 0) touchedGround = true;
        }
        if(tetromino.getPivot().y <= 0) touchedGround = true;

        if(touchedGround) {
            board.setTetroToBoard(tetromino);
            tetromino.realocate(new Vector2(5,18));
            timer = 0;

        }
    }

}

