package com.mygdx.game.entity.tetro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Block implements Comparable{

    private Vector2 pos;
    private Color color;


    public Block(Vector2 pos,Color color) {
        this.pos = pos;
        this.color = color;
    }



    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setPos(float x,float y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    public float getX() {return pos.x;}

    public void setX(float x) {pos.x = x;}

    public float getY() {return pos.y;}

    public void setY(float y) {pos.y = y;}



    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int compareTo(Object o) {

        float difference = ((Block)o).pos.y;

        return (int)(this.pos.y - difference);
    }
}
