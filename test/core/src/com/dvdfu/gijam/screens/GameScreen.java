package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.handlers.ObjectPool;
import com.dvdfu.gijam.objects.Block;
import com.dvdfu.gijam.objects.Chaser;
import com.dvdfu.gijam.objects.PowerUp;

public class GameScreen extends AbstractScreen {
	private GameStage stage;
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
		chaser.update();
		powerUpController();
		blockController();
		stage.act(delta);

		stage.draw();
	}

	private void powerUpController() {
		if (powerUpCounter > 0) {
			System.out.println(powerUpCounter);
			powerUpCounter -= 1;
		} else if (powerUpCounter == 0) {
			PowerUp powerUp = pool.getPowerUp();
			powerUps.addActor(powerUp);
			powerUp.setPosition(Consts.ScreenWidth, MathUtils.random(10, Consts.ScreenHeight - 10));
			powerUpCounter = MathUtils.random(600, 1200);
		}
	}

	private void blockController() {
		if (Input.MousePressed() && currentBlock == null) {
			origX = Input.MouseX();
			origY = Input.MouseY();
			if (origX < Consts.DrawAreaRight) { // if the clicked area is not
												// "drawable area"
				origX = Consts.DrawAreaRight; // put it as the edge of the
												// drawable area
			} else if (origX > Consts.ScreenWidth) {
				origX = Consts.ScreenWidth;
			}
			if (origY < 0) { // if clicked area is below the screen
				origY = 0; // set the Y coord as 0 (bottom of the page)
			} else if (origY > Consts.ScreenHeight) {
				origY = Consts.ScreenHeight;
			}

			currentBlock = pool.getBlock();
			blocks.addActor(currentBlock);
		} else if (Input.MouseDown() && currentBlock != null) {
			newX = Input.MouseX();
			newY = Input.MouseY();

			if (newX < Consts.DrawAreaRight) { // if new selection goes below
												// half of the screen
				newX = Consts.DrawAreaRight; // set it on the edge of the screen
			} else if (newX > Consts.ScreenWidth) {
				newX = Consts.ScreenWidth;
			}
			if (newY < 0) { // if new selection Y-coord goes below the screen
				newY = 0; // set it on the edge of the screen
			} else if (newY > Consts.ScreenHeight) {
				newY = Consts.ScreenHeight;
			}

			float width = Math.abs(newX - origX);
			float height = Math.abs(newY - origY);
			currentBlock.setPosition(Math.min(newX, origX),
					Math.min(newY, origY));
			currentBlock.setSize(width, height);
		} else if (Input.MouseReleased() && currentBlock != null) {
			// if (currentBlock.getWidth() * currentBlock.getHeight() > 500) {
			currentBlock.createBlock();
			currentBlock = null;
			// }
		}

		for (Actor actor : blocks.getChildren()) {
			Block block = (Block) actor;
			chaser.collideBlock(block);
			if (block.isDead()) {
				blocks.removeActor(block);
				pool.free(block);
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
