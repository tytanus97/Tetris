package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.TetrisGame;

public class Block extends Image {

    private Vector2 position;
    private Rectangle body;
    private float timer;

    public Block() {
        position = new Vector2(5*TetrisGame.BLOCK_DIV,TetrisGame.GAME_HEIGHT-50);
        body = new Rectangle(position.x,position.y,50,50);
        timer = 0;
    }

    public void update(float dt) {
        timer +=dt;
        if(timer>1){
            position.y -=50;
            timer = 0;
        }
    }
    public Vector2 getPosition() {
        return this.position;
    }
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(this.position.x,this.getPosition().y,50,50);
    }

}
