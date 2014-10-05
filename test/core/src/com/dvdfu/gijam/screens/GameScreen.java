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

public class GameScreen extends AbstractScreen {
	private GameStage stage;
	private ObjectPool pool;
	private Chaser chaser;
	private Group blocks;

	private Block currentBlock;
	private float origX;
	private float origY;

	private float newX;
	private float newY;
	private float savedX;
	private float savedY;
	
	private float MaxLeftG = 500f;
	private float MaxRightG = 40000f;
	private float LeftGauge = 500f;
	private float RightGauge = 40000f;

	private float width;
	private float height;
	
	private float MaxAWidth;
	private float MaxAHeight;

	private float minDimSize = 50f;


	private int cnter = 0;

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
		if (RightGauge < 0) {
			RightGauge = 0;
		} else if (RightGauge > MaxRightG) {
			RightGauge = MaxRightG;
		}
		GaugeController();
		System.out.println(LeftGauge + " : " + RightGauge);
		chaser.update();
		blockController();
		stage.act(delta);
		stage.draw();
	}

	private void GaugeController() {
		if (LeftGauge < MaxLeftG) {
			LeftGauge += 0.05f;
		}
		if (RightGauge < MaxRightG) {
			RightGauge += 50f;
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

			newX = MathUtils.clamp(Input.MouseX(), Consts.DrawAreaRight,
					Consts.ScreenWidth);
			newY = MathUtils.clamp(Input.MouseY(), 0, Consts.ScreenHeight);

			width = Math.abs(newX - origX);
			height = Math.abs(newY - origY);
			
			width = MathUtils.clamp(width, minDimSize, Consts.ScreenWidth);
			height = MathUtils.clamp(height, minDimSize, Consts.ScreenHeight);
			
			if(newX > origX)
			{
				newX = origX + width;
			}
			else if(newX < origX)
			{
				newX = origX - width;
			}
			if(newY > origY)
			{
				newY = origY + height;
			}
			else if(newY < origY)
			{
				newY = origY - height;
			}
			
			if (width * height <= RightGauge) {
				savedX = newX;
				savedY = newY;
				MaxAHeight = height;
				MaxAWidth = width;
				currentBlock.setPosition(Math.min(newX, origX),
						Math.min(newY, origY));
				currentBlock.setSize(width, height);
			}

		} else if (Input.MouseReleased() && currentBlock != null) {
			// if (currentBlock.getWidth() * currentBlock.getHeight() > 500) {
			if(width * height <= RightGauge)
			{
			currentBlock.createBlock();
			RightGauge -= (width * height);
			}
			else if(RightGauge >= 2500)
			{
				currentBlock.setSize(MaxAWidth, MaxAHeight);
				currentBlock.setPosition(Math.min(savedX, origX), Math.min(savedY, origY));
				currentBlock.createBlock();
				RightGauge -= (MaxAWidth * MaxAHeight);
				MaxAHeight = minDimSize;
				MaxAWidth = minDimSize;
				//blocks.removeActor(currentBlock);
				//pool.free(currentBlock);
			}
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
