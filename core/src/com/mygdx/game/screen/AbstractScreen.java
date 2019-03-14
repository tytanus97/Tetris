package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.TetrisGame;

public abstract class AbstractScreen implements Screen {


    protected TetrisGame game;


    public AbstractScreen(TetrisGame game) {
        this.game = game;

        init();
    }
    protected abstract void init();


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
