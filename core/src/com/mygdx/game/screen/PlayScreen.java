package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.controller.BoardController;
import com.mygdx.game.controller.MoveController;
import com.mygdx.game.controller.TetrominoController;
import com.mygdx.game.entity.board.Board;
import com.mygdx.game.entity.tetro.*;

public class PlayScreen extends AbstractScreen{

    private ShapeRenderer shapeRenderer;
    private float timer = 0;
    private MoveController moveController;
    private BoardController boardController;
    private Board board;
    private Tetromino tetromino;
    private float tickTime;
    private TetrominoController tetrominoController;
    private Music music;


    public PlayScreen(TetrisGame tetrisGame) {
        super(tetrisGame);

        tetromino  = tetrominoController.getRandomTetromino();
       // music = Gdx.audio.newMusic(Gdx.files.internal("./music/tetris.mp3"));
       //    music.play();
    }

    @Override
    protected void init() {
        shapeRenderer = new ShapeRenderer();
        moveController = new MoveController();
        boardController = new BoardController();
        tetrominoController = new TetrominoController();


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

        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && canMoveSide(-1) && onLeftBound()) {
            moveController.move(tetromino,-1);

        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D)  && canMoveSide(1) && onRightBound()) {
            moveController.move(tetromino,1);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.tickTime = 0.02f;
        }
        else this.tickTime = 0.5f;
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            tetrominoController.rotateLeft(this.tetromino);
        }
    }

    private boolean onLeftBound() {
        for(Block block:tetromino.getBlockList()) {
            if(block.getPos().x <=0) {
                return false;
            }
        }
        return true;
    }
    private boolean onRightBound() {
        for(Block block:tetromino.getBlockList()) {
            if(block.getPos().x+1 >=this.board.getWidth()) {
                return false;
            }
        }
        return true;
    }

    private boolean canMoveSide(int direction) {

        for(Block boardBlock: board.getBlocks()) {
            for(Block tetroBlock: tetromino.getBlockList()) {
                if(tetroBlock.getPos().x+direction == boardBlock.getPos().x
                        && tetroBlock.getPos().y == boardBlock.getPos().y) {
                    return false;
                }
            }
        }
        return true;
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
            shapeRenderer.rectLine(new Vector2(i* TetrisGame.BLOCK_DIV,0)
                                    ,new Vector2(i*TetrisGame.BLOCK_DIV,TetrisGame.GAME_HEIGHT),1);
        }
        for(int i=1;i<(TetrisGame.GAME_HEIGHT/TetrisGame.BLOCK_DIV);i++)
        {
            shapeRenderer.rectLine(new Vector2(0,i*TetrisGame.BLOCK_DIV)
                                    ,new Vector2(TetrisGame.GAME_WIDTH,i*TetrisGame.BLOCK_DIV),1);
        }
    }

    private void update(float delta) {

        //shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());

        this.board = (boardController.syncBoard(tetromino,this.board));
        timer +=delta;
        if(timer > tickTime) {
            checkColisionWithGround();
            checkVerticalCollisionWithBlocks();
            tetromino = moveController.moveDown(tetromino);
            timer = 0f;
        }
    }


    private void checkVerticalCollisionWithBlocks() {

        boolean collision = false;

        for(Block boardBlock: board.getBlocks()) {
            for(Block tetroBlock: tetromino.getBlockList()) {
                if(tetroBlock.getPos().y-1 == boardBlock.getPos().y
                        && tetroBlock.getPos().x == boardBlock.getPos().x) {
                    collision = true;
                    break;
                }
            }
            if(collision) break;
        }
        if(collision) {
            this.board.setTetroToBoard(this.tetromino);
            this.tetromino = tetrominoController.getRandomTetromino();
            timer = 0f;
        }
    }

    private void checkColisionWithGround() {

        boolean touchedGround = false;

        for(Block block:tetromino.getBlockList()) {
            if(block.getPos().y <= 0) touchedGround = true;
        }
        if(tetromino.getPivot().y <= 0) touchedGround = true;

        if(touchedGround) {
            board.setTetroToBoard(tetromino);
            //tetromino.realocate(new Vector2(5,18));
            this.tetromino = tetrominoController.getRandomTetromino();
            timer = 0;
        }
    }
    public void dispose() {
        music.dispose();

    }

}

