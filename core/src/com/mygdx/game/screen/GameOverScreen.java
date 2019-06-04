package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TetrisGame;

public class GameOverScreen extends AbstractScreen{

    private int points;
    private TextButton againButton;
    private Stage stage;
    private Viewport vp;
    private Skin skin;

    public GameOverScreen(final TetrisGame game,int points) {
        super(game);
        this.vp =new FitViewport(TetrisGame.GAME_WIDTH,TetrisGame.GAME_HEIGHT,new OrthographicCamera());
        this.stage = new Stage(vp,this.game.sb);
        this.points = points;


        skin = new Skin(Gdx.files.internal("core/assets/uiskin.json"));
        this.againButton = new TextButton("Play again",this.skin);
        this.againButton.setSize(200,50);
        this.againButton.setPosition(TetrisGame.GAME_WIDTH/2-againButton.getWidth()/2,TetrisGame.GAME_WIDTH);


        this.stage.addActor(againButton);


        Gdx.input.setInputProcessor(this.stage);


        this.againButton.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("Przycisk Nacisniety!!!");
                PlayScreen.POINTS = 0;
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });



    }


    @Override
    protected void init() {


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();


    }

    @Override
    public void dispose() {
        super.dispose();
        this.stage.dispose();
        this.skin.dispose();
        System.out.println("Disposed");
    }
}
