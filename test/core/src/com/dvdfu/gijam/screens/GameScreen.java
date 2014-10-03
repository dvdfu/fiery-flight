package com.dvdfu.gijam.screens;

import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.objects.Player;

public class GameScreen extends AbstractScreen {
	private GameStage stage;
	private Player player;

	public GameScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		player = new Player(stage);
		stage.addActor(player);
	}
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	public void resize(int width, int height) {}

	public void show() {}

	public void hide() {}

	public void pause() {}

	public void resume() {}

	public void dispose() {
		stage.dispose();
	}
}
