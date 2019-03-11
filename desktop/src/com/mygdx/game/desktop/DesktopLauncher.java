package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TetrisGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = TetrisGame.GAME_WIDTH;
		config.height = TetrisGame.GAME_HEIGHT;
		config.resizable = false;
		config.title = TetrisGame.TITLE;

		new LwjglApplication(new TetrisGame(), config);
	}
}
