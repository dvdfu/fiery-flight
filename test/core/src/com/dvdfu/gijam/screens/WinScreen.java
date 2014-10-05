package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Gdx;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class WinScreen extends AbstractScreen {
	private GameStage stage;
	public WinScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		stage.setCamPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
		stage.getSpriteBatch().begin();
		stage.getSpriteBatch().draw(Sprites.win, Consts.ScreenWidth / 2 - 180, Consts.ScreenHeight / 2);
		stage.getSpriteBatch().end();
		if (Input.MousePressed()) {
			game.exitScreen();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
