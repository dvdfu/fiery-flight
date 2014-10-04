package com.dvdfu.gijam.screens;

import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.objects.BlockController;
import com.dvdfu.gijam.objects.Chaser;

public class GameScreen extends AbstractScreen {
	private GameStage stage;
	private Chaser chaser;
	private BlockController bc;
	
	public GameScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		chaser = new Chaser(stage);
		stage.addActor(chaser);
		bc = new BlockController(stage);
		stage.addActor(bc);
		//stage.setCamPosition(0, 0);
	}
	public void render(float delta) {
		stage.getCamera().unproject(Input.mouse);
		bc.act();
		
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
