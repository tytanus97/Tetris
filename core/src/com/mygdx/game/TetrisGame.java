package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.screen.PlayScreen;

public class TetrisGame extends Game {


	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 950;
	public static final int BLOCK_DIV = 50;
	public  static final int BLOCK_SIZE = 50;
	public static final String TITLE = "Tetris";


	private boolean isPaused = false;

	@Override
	public void create () {

		setScreen(new PlayScreen(this));

	}

	public void pause(boolean paused) {
		isPaused = paused;
	}
	public boolean getPauser() {
		return this.isPaused;
	}

}
