package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.TetrisGame;

public abstract class AbstractScreen implements Screen {

    protected OrthographicCamera camera;
    protected TetrisGame game;
    protected Stage stage;
    protected SpriteBatch sb;

    public AbstractScreen(TetrisGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(TetrisGame.GAME_WIDTH,TetrisGame.GAME_HEIGHT));
        sb = new SpriteBatch();
        createCamera();
        Gdx.input.setInputProcessor(this.stage);
        init();
    }
    protected abstract void init();

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,TetrisGame.GAME_WIDTH,TetrisGame.GAME_HEIGHT);
        camera.update();
    }

    private void clearScreen() {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        clearScreen();
        camera.update();
        sb.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
