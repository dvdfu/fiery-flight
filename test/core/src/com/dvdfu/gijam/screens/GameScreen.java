package com.dvdfu.gijam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.Enums;
import com.dvdfu.gijam.handlers.Enums.ParticleType;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.handlers.ObjectPool;
import com.dvdfu.gijam.objects.Background;
import com.dvdfu.gijam.objects.Block;
import com.dvdfu.gijam.objects.Chaser;
import com.dvdfu.gijam.objects.Fireball;
import com.dvdfu.gijam.objects.Particle;
import com.dvdfu.gijam.objects.PowerUp;

public class GameScreen extends AbstractScreen {
	private GameStage stage;
	private Background bg;
	private ObjectPool pool;
	private Chaser chaser;
	private Group blocks;
	private Group powerUps;
	private Group particles;
	private Group fireballs;

	private Block currentBlock;
	private float origX;
	private float origY;

	private float newX;
	private float newY;
	private float savedX;
	private float savedY;

	private float playerMeterMax = 500;
	private float playerMeter = playerMeterMax;
	private float blockMeterMax = 400000;
	private float blockMeter = blockMeterMax;

	private float width;
	private float height;

	private float MaxAWidth;
	private float MaxAHeight;

	private float minDimSize = 50f;

	private int powerUpCounter = MathUtils.random(300, 600);
	private int fireballCounter;

	public GameScreen(MainGame game) {
		super(game);
		stage = new GameStage();
		pool = new ObjectPool(stage);
		bg = new Background(stage);
		stage.addActor(bg);

		chaser = new Chaser(stage);
		chaser.setPosition(200, 200);
		stage.addActor(chaser);
		blocks = new Group();
		stage.addActor(blocks);

		Block newBlock = pool.getBlock();
		newBlock.setSize(500, 200);
		newBlock.setY(-40);
		newBlock.createBlock();
		blocks.addActor(newBlock);

		powerUps = new Group();
		stage.addActor(powerUps);

		particles = new Group();
		stage.addActor(particles);

		fireballs = new Group();
		stage.addActor(fireballs);

		stage.setCamPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
	}

	public void render(float delta) {
		if (fireballCounter > 0) {
			fireballCounter--;
		} else {
			Fireball fireball = pool.getFireball();
			fireball.setPosition(Input.MouseX(), Input.MouseY());
			fireballs.addActor(fireball);
			fireballCounter = 1;
		}
		
		if (blockMeter < 0) {
			blockMeter = 0;
		} else if (blockMeter > blockMeterMax) {
			blockMeter = blockMeterMax;
		}
		GaugeController();
		System.out.println(playerMeter + " : " + blockMeter);
		bg.update();
		chaser.update();
		powerUpController();
		blockController();
		stage.act(delta);
		stage.draw();
	}

	private void GaugeController() {
		if (playerMeter < playerMeterMax) {
			playerMeter += 0.05f;
		}
		if (blockMeter < blockMeterMax) {
			blockMeter += 50f;
		}
	}

	private void powerUpController() {
		if (powerUpCounter > 0) {
			powerUpCounter--;
		} else {
			PowerUp powerUp = pool.getPowerUp();
			powerUps.addActor(powerUp);
			powerUpCounter = MathUtils.random(300, 600);
		}

		for (int i = 0; i < powerUps.getChildren().size; i++) {
			PowerUp powerUp = (PowerUp) powerUps.getChildren().get(i);
			powerUp.update();
			chaser.collidePowerUp(powerUp);
			if (powerUp.isDead()) {
				powerUps.removeActor(powerUp);
				pool.free(powerUp);
			}
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
				for (float j = block.getX(); j < block.getRight(); j += 32) {
					for (float k = block.getY(); k < block.getTop(); k += 32) {
						Particle particle = pool.getParticle();
						particle.setType(Enums.ParticleType.ROCK);
						particle.setPosition(j, k);
						particles.addActor(particle);
					}
				}
				blockMeter += block.getWidth() * block.getHeight();
				blocks.removeActor(block);
				pool.free(block);
				i--;
			}
		}

		for (Actor actor : particles.getChildren()) {
			Particle particle = (Particle) actor;
			if (particle.dead()) {
				particles.removeActor(actor);
				pool.free(particle);
			}
		}

		for (Actor actor : fireballs.getChildren()) {
			Fireball fireball = (Fireball) actor;
			fireball.target(chaser.getX(), chaser.getY());
			if (fireball.isDead()) {
				fireballs.removeActor(actor);
				pool.free(fireball);
			}
			
			Particle particle = pool.getParticle();
			particle.setPosition(fireball.getX(), fireball.getY());
			particle.setType(ParticleType.FIRE);
			particles.addActor(particle);
		}

		if (Input.MousePressed() && currentBlock == null) {
			origX = MathUtils.clamp(Input.MouseX(), Consts.DrawAreaRight,
					Consts.ScreenWidth);
			origY = MathUtils.clamp(Input.MouseY(), 0, Consts.ScreenHeight);

			currentBlock = pool.getBlock();
			blocks.addActor(currentBlock);
		} else if (Input.MouseDown() && currentBlock != null) {
			origX -= Consts.ScreenSpeed;
			savedX -=Consts.ScreenSpeed;

			newX = MathUtils.clamp(Input.MouseX(), Consts.DrawAreaRight,
					Consts.ScreenWidth);
			newY = MathUtils.clamp(Input.MouseY(), 0, Consts.ScreenHeight);

			width = Math.abs(newX - origX);
			height = Math.abs(newY - origY);

			width = MathUtils.clamp(width, minDimSize, Consts.ScreenWidth);
			height = MathUtils.clamp(height, minDimSize, Consts.ScreenHeight);

			if (newX > origX) {
				newX = origX + width;
			} else if (newX < origX) {
				newX = origX - width;
			}
			if (newY > origY) {
				newY = origY + height;
			} else if (newY < origY) {
				newY = origY - height;
			}

			if (width * height <= blockMeter) {
				savedX = newX;
				savedY = newY;
				MaxAHeight = height;
				MaxAWidth = width;
				currentBlock.setPosition(Math.min(newX, origX),
						Math.min(newY, origY));
				currentBlock.setSize(width, height);
			}

		} else if (Input.MouseReleased() && currentBlock != null) {
			if (width * height <= blockMeter) {
				currentBlock.createBlock();
				blockMeter -= (width * height);
			} else if (blockMeter >= 2500) {
				currentBlock.setSize(MaxAWidth, MaxAHeight);
				currentBlock.setPosition(Math.min(savedX, origX),
						Math.min(savedY, origY));
				currentBlock.createBlock();
				blockMeter -= (MaxAWidth * MaxAHeight);
				MaxAHeight = minDimSize;
				MaxAWidth = minDimSize;
				// blocks.removeActor(currentBlock);
				// pool.free(currentBlock);
			}
			height = 0;
			width = 0;
			origX = 0;
			origY =0;
			newX = 0;
			newY =0;
			savedX = 0;
			savedY =0;
			MaxAWidth =0;
			MaxAHeight=0;
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
