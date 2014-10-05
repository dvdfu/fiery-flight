package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.handlers.ObjectPool;
import com.dvdfu.gijam.objects.Background;
import com.dvdfu.gijam.objects.Block;
import com.dvdfu.gijam.objects.Chaser;
import com.dvdfu.gijam.objects.PowerUp;

public class GameScreen extends AbstractScreen {
	private GameStage stage;
	private Background bg;
	private ObjectPool pool;
	private Chaser chaser;
	private Group blocks;
	private Group powerUps;

	private Block currentBlock;
	private float origX;
	private float origY;

	private float newX;
	private float newY;

	private int powerUpCounter = MathUtils.random(600, 1200);

	public GameScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		pool = new ObjectPool(stage);
		bg = new Background(stage);
		stage.addActor(bg);

		chaser = new Chaser(stage);
		stage.addActor(chaser);
		blocks = new Group();
		stage.addActor(blocks);
		powerUps = new Group();
		stage.addActor(powerUps);

		stage.setCamPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
	}

	public void render(float delta) {
		bg.update();
		chaser.update();
		powerUpController();
		blockController();
		stage.act(delta);

		stage.draw();
	}

	private void powerUpController() {
		if (powerUpCounter > 0) {
			powerUpCounter--;
		} else {
			PowerUp powerUp = pool.getPowerUp();
			powerUps.addActor(powerUp);
			powerUp.setPosition(Consts.ScreenWidth,
					MathUtils.random(10, Consts.ScreenHeight - 10));
			powerUpCounter = MathUtils.random(600, 1200);
		}
	}

	private void blockController() {
		for (int i = 0; i < blocks.getChildren().size; i++) {
			Block block = (Block) blocks.getChildren().get(i);
			block.update();
			chaser.collideBlock(block);
			for (int j = i + 1; j < blocks.getChildren().size; j++) {
				Block other = (Block) blocks.getChildren().get(j);
				block.collideBlock(other);
				other.collideBlock(block);
			}
			if (block.isDead()) {
				blocks.removeActor(block);
				pool.free(block);
			}
		}
		
		if (Input.MousePressed() && currentBlock == null) {
			origX = MathUtils.clamp(Input.MouseX(), Consts.DrawAreaRight,
					Consts.ScreenWidth);
			origY = MathUtils.clamp(Input.MouseY(), 0, Consts.ScreenHeight);

			currentBlock = pool.getBlock();
			blocks.addActor(currentBlock);
		} else if (Input.MouseDown() && currentBlock != null) {
			origX -= Consts.ScreenSpeed;
			newX = MathUtils.clamp(Input.MouseX(), Consts.DrawAreaRight,
					Consts.ScreenWidth);
			newY = MathUtils.clamp(Input.MouseY(), 0, Consts.ScreenHeight);

			float width = Math.abs(newX - origX);
			float height = Math.abs(newY - origY);
			currentBlock.setPosition(Math.min(newX, origX),
					Math.min(newY, origY));
			currentBlock.setSize(width, height);
		} else if (Input.MouseReleased() && currentBlock != null) {
			currentBlock.createBlock();
			currentBlock = null;
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
