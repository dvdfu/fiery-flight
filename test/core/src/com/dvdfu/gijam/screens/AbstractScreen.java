package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Screen;
import com.dvdfu.gijam.MainGame;

public abstract class AbstractScreen implements Screen {
	protected MainGame game;

	public AbstractScreen(MainGame game) {
		this.game = game;
	}

	public abstract void render(float delta);

	public abstract void resize(int width, int height);

	public abstract void show();

	public abstract void hide();

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();
}
