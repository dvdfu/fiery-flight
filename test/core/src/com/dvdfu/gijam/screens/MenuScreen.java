package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Gdx;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.objects.Background;
import com.dvdfu.gijam.visuals.Sprites;

public class MenuScreen extends AbstractScreen {
	private GameStage stage;
	private Background bg;

	public MenuScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		bg = new Background(stage);
		stage.addActor(bg);

		stage.setCamPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
	}

	@Override
	public void render(float delta) {
		bg.update();
		stage.act(delta);
		stage.draw();
		stage.getSpriteBatch().begin();
		stage.getSpriteBatch().draw(Sprites.menu, Consts.ScreenWidth / 2 - 180, Consts.ScreenHeight / 2);
		stage.getSpriteBatch().end();
		if (Input.MousePressed()) {
			game.enterScreen(new GameScreen(game));
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
