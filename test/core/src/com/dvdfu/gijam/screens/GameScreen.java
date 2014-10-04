package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.handlers.ObjectPool;
import com.dvdfu.gijam.objects.Block;
import com.dvdfu.gijam.objects.Chaser;

public class GameScreen extends AbstractScreen {
	private GameStage stage;
	private ObjectPool pool;
	private Chaser chaser;
	private Group blocks;

	private Block currentBlock;
	private float origX;
	private float origY;

	public GameScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		pool = new ObjectPool(stage);

		chaser = new Chaser(stage);
		stage.addActor(chaser);
		blocks = new Group();
		stage.addActor(blocks);

		stage.setCamPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
	}

	public void render(float delta) {
		blockController();
		stage.act(delta);
		stage.draw();
	}

	private void blockController() {
		if (Input.MousePressed() && currentBlock == null) {
			origX = Input.MouseX();
			origY = Input.MouseY();
			currentBlock = pool.getBlock();
			blocks.addActor(currentBlock);
		} else if (Input.MouseDown() && currentBlock != null) {
			float width = Math.abs(Input.MouseX() - origX);
			float height = Math.abs(Input.MouseY() - origY);
			currentBlock.setPosition(Math.min(Input.MouseX(), origX),
					Math.min(Input.MouseY(), origY));
			currentBlock.setSize(width, height);
		} else if (Input.MouseReleased() && currentBlock != null) {
			// if (currentBlock.getWidth() * currentBlock.getHeight() > 500) {
			currentBlock.createBlock();
			currentBlock = null;
			// }
		}
		
		for (Actor actor : blocks.getChildren()) {
			Block block = (Block) actor;
			if (block.isDead()) {
				blocks.removeActor(block);
				pool.free(block);
				System.out.println(blocks.getChildren().size);
			}
		}
	}

	public void resize(int width, int height) {
	}

	public void show() {
	}

	public void hide() {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
		stage.dispose();
	}

	public ObjectPool getPool() {
		return pool;
	}
}
