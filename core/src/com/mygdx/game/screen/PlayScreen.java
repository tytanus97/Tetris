package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.controller.BoardController;
import com.mygdx.game.controller.MoveController;
import com.mygdx.game.controller.TetrominoController;
import com.mygdx.game.entity.board.Board;
import com.mygdx.game.entity.tetro.Block;
import com.mygdx.game.entity.tetro.Tetromino;

public class PlayScreen extends AbstractScreen{


    public static int POINTS = 0;

    private ShapeRenderer shapeRenderer;
    private float timer = 0;
    private MoveController moveController;
    private BoardController boardController;
    private Board board;
    private Tetromino tetromino;
    private float tickTime;
    private TetrominoController tetrominoController;
    private Music music;
    private Hud hud;



    public PlayScreen(TetrisGame tetrisGame) {
        super(tetrisGame);

        tetromino  = tetrominoController.getRandomTetromino();
       // music = Gdx.audio.newMusic(Gdx.files.internal("./music/tetris.mp3"));
        /*music.setLooping(true);
           music.play();*/


    }

    @Override
    protected void init() {
        shapeRenderer = new ShapeRenderer();
        moveController = new MoveController();
        boardController = new BoardController();
        tetrominoController = new TetrominoController();
        hud = new Hud(this.game.sb);

        board = new Board();
        tickTime = 0.5f;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        checkInput();
        update(delta);
        draw();
        this.game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void checkInput() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && this.tetromino.canMoveSide(-1,board.getBlocks())
                && this.tetromino.onLeftBound()) {
            moveController.move(tetromino,-1);

        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D)  && this.tetromino.canMoveSide(1,board.getBlocks())
                && this.tetromino.onRightBound(this.board.getWidth())) {
            moveController.move(tetromino,1);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.tickTime = 0.02f;
        }
        else this.tickTime = 0.5f;
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            tetrominoController.rotate(this.tetromino,0);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            tetrominoController.rotate(this.tetromino,1);
        }
    }






    private void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        tetromino.draw(shapeRenderer);
        board.draw(shapeRenderer);
        board.drawLines(shapeRenderer);
        shapeRenderer.end();

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

        hud.update();


        if(board.checkGameOver()) {
            this.game.setScreen(new GameOverScreen(this.game, POINTS));
            this.dispose();
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
            this.board.scoreBoard();
            this.tetromino = tetrominoController.getRandomTetromino();
            timer = 0f;
            POINTS++;
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
            this.board.scoreBoard();
            //tetromino.realocate(new Vector2(5,18));
            this.tetromino = tetrominoController.getRandomTetromino();
            timer = 0;
            POINTS++;

        }
    }
    public void dispose() {
        super.dispose();
       // music.dispose();
        hud.dispose();
        System.out.println("Disposed correctly");

    }

}

