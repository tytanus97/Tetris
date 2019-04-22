package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TetrisGame;

public class Hud implements Disposable {

    private Viewport viewport;
    public Stage stage;

    private Label pointLabel;
    private Label points;
    private BitmapFont bitmapFont;

    public Hud(SpriteBatch sb) {

        viewport = new FitViewport(TetrisGame.GAME_WIDTH,TetrisGame.GAME_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);
        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(2);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        pointLabel = new Label("Points", new Label.LabelStyle(bitmapFont, Color.BLACK));

        points = new Label(String.format("%03d",PlayScreen.POINTS), new Label.LabelStyle(bitmapFont, Color.BLACK));

        table.add(pointLabel).expandX().padTop(10).padRight(400);
        table.row();
        table.add(points).padRight(400);

        stage.addActor(table);

        }

        public void update() {

        points.setText(PlayScreen.POINTS);
        }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


