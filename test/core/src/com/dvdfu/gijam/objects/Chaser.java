package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class Chaser extends GameObject {
	private float moveMax = 5;
	private float moveSpeed = 0.3f;

	private int jumpsMax = 2;
	private int jumpsLeft = jumpsMax;
	private float jumpSpeed = 7;

	private int currentPowerUp;
	private int powerUpCounter;

	private boolean dashing;
	private int dashCounter;
	private float dashSpeedX, dashSpeedY;

	private float playerMeterMax = 500;
	private float playerMeter = playerMeterMax;
	private int playerMeterCounter = 200;
	private boolean dead;

	public Chaser(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(48, 48);
		dead = false;
	}

	public void collideBlock(Block block) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(block.bounds) && block.isCreated()) {
			if (getY() >= block.getTop()) {
				setY(block.getTop());
				jumpsLeft = jumpsMax;
				ySpeed = 0;
			} else if (getTop() <= block.getY()) {
				setY(block.getY() - getHeight());
				ySpeed = 0;
			}
			if (getX() >= block.getRight()) {
				setX(block.getRight());
				xSpeed = 0;
			} else if (getRight() <= block.getX()) {
				setX(block.getX() - getWidth());
				xSpeed = 0;
			}
		}
		setBounds();
		if (bounds.overlaps(block.bounds) && block.isCreated()) {
			setY(block.getTop());
			jumpsLeft = jumpsMax;
			ySpeed = 0;
		}
	}

	public float getMeter()
	{
		return playerMeter;
	}
	public float getMaxMeter()
	{
		return playerMeterMax;
	}
	public void collidePowerUp(PowerUp powerUp) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(powerUp.bounds)) {
			currentPowerUp = powerUp.getType();
			powerUpCounter = 300;
			powerUp.setDead();
		}
	}

	public void collideFireball(Fireball fireball) {
		if (bounds.overlaps(fireball.bounds)) {
			//dead = true;
			xSpeed = fireball.getXSpeed();
			ySpeed = fireball.getYSpeed();
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 1, 1, 1);
		super.draw(batch, parentAlpha);
	}

	public void act(float delta) {
		setX(getX() - Consts.ScreenSpeed);
		if (getTop() > Consts.ScreenHeight) {
			setY(Consts.ScreenHeight - getHeight());
			ySpeed = 0;
		}
		super.act(delta);
	}

	public boolean isDead() {
		return getTop() < 0 || dead || getRight() < 0;
	}

	public void update() {
		if (playerMeter < playerMeterMax) {
			playerMeterCounter--;
			if (playerMeterCounter == 0) {
				playerMeter += 100;
				playerMeterCounter = 200;
			}
		} else if (playerMeter > playerMeterMax) {
			playerMeter = playerMeterMax;
		}
		move();
	}

	public void move() {
		if (!dashing) {
			ySpeed -= Consts.Gravity;
		}

		if (currentPowerUp != 0) {
			if (powerUpCounter == 0) {
				currentPowerUp = 0;
				jumpSpeed = 7f;
				moveSpeed = 0.3f;
				moveMax = 5;
			} else {
				powerUpCounter--;
			}
			if (currentPowerUp == 1) {
				moveSpeed = 0.6f;
				moveMax = 8;
			} else if (currentPowerUp == 2) {
				jumpSpeed = 10f;
			}
		}

		if (Input.KeyDown(Input.Z)
				&& (playerMeter >= 200)
				&& !dashing
				&& (Input.KeyDown(Input.ARROW_UP)
						|| Input.KeyDown(Input.ARROW_DOWN)
						|| Input.KeyDown(Input.ARROW_LEFT) || Input
							.KeyDown(Input.ARROW_RIGHT))) {
			playerMeter -= 200f;
			jumpsLeft = 0;
			xSpeed = 0;
			ySpeed = 0;
			dashing = true;
			dashCounter = 20;
			if (Input.KeyDown(Input.ARROW_UP)
					|| (Input.KeyDown(Input.ARROW_UP) && Input
							.KeyDown(Input.ARROW_DOWN))) {
				dashSpeedY = 2f;
			} else if (Input.KeyDown(Input.ARROW_DOWN)) {
				dashSpeedY = -2f;
			} else {
				dashSpeedY = 0;
			}
			if (Input.KeyDown(Input.ARROW_RIGHT)
					|| (Input.KeyDown(Input.ARROW_RIGHT) && Input
							.KeyDown(Input.ARROW_LEFT))) {
				dashSpeedX = 2f;
			} else if (Input.KeyDown(Input.ARROW_LEFT)) {
				dashSpeedX = -2f;
			} else {
				dashSpeedX = 0;
			}
		}

		if (dashing && dashCounter > 0) {
			if (dashCounter > 10) {
				xSpeed += dashSpeedX;
				ySpeed += dashSpeedY;
			} else if (dashCounter <= 8) {
				if (xSpeed != 0) {
					xSpeed -= dashSpeedX;
				}
				if (ySpeed != 0) {
					ySpeed -= dashSpeedY;
				}
			}
			dashCounter--;
		} else {
			dashing = false;
			if (Input.KeyPressed(Input.ARROW_UP) && jumpsLeft > 0) {
				ySpeed = jumpSpeed;
				jumpsLeft--;
			}
			if (Input.KeyDown(Input.ARROW_RIGHT)) {
				if (xSpeed < moveMax) {
					xSpeed += moveSpeed;
				} else
					xSpeed = moveMax;
			} else if (xSpeed > 0) {
				xSpeed -= moveSpeed;
			}
			if (Input.KeyDown(Input.ARROW_LEFT)) {
				if (xSpeed > -moveMax) {
					xSpeed -= moveSpeed;
				} else
					xSpeed = -moveMax;
			} else if (xSpeed < 0) {
				xSpeed += moveSpeed;
			}
			if (xSpeed > -moveSpeed && xSpeed < moveSpeed) {
				xSpeed = 0;
			}
		}
	}

	public boolean isDashing() {
		return dashing;
	}
}
