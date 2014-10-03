package com.dvdfu.gijam;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.handlers.InputController;
import com.dvdfu.gijam.screens.AbstractScreen;
import com.dvdfu.gijam.screens.GameScreen;

public class MainGame extends Game {
	private Stack<AbstractScreen> screens;

	public void create() {
		Gdx.input.setInputProcessor(new InputController());
		screens = new Stack<AbstractScreen>();
		enterScreen(new GameScreen(this));
	}

	public void enterScreen(AbstractScreen screen) {
		if (!screens.isEmpty()) {
			screens.peek().pause();
		}
		screens.push(screen);
		setScreen(screens.peek());
	}

	public void changeScreen(AbstractScreen screen) {
		if (screens.isEmpty()) {
			return;
		}
		screens.pop();
		screens.push(screen);
		setScreen(screens.peek());
	}

	public void exitScreen() {
		if (screens.isEmpty()) {
			Gdx.app.exit();
		}
		screens.pop();
		screens.peek().resume();
		setScreen(screens.peek());
	}

	public void dispose() {
	}

	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		if (getScreen() != null) {
			super.render();
		}
		Input.update();
	}

	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void pause() {
	}

	public void resume() {
	}
}