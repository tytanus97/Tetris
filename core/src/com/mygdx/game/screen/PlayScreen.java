package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.TetrisGame;
import com.mygdx.game.entity.Block;

public class PlayScreen extends AbstractScreen{

    private Block block;
    private ShapeRenderer shapeRenderer;
    private int[][] board = new int[10][19];

    public PlayScreen(TetrisGame tetrisGame) {
        super(tetrisGame);
        block = new Block();
        shapeRenderer = new ShapeRenderer();

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
        shapeRenderer.setColor(Color.BLACK);
        block.draw(shapeRenderer);
        shapeRenderer.end();

    }

    private void update(float delta) {
        block.update(delta);
        shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());
    }



}
