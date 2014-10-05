package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class Chaser extends GameObject {
	private float moveMax = 5;
	private float moveSpeed = 0.3f;

	private boolean grounded, groundedBuffer;
	private int jumpsMax = 2;
	private int jumpsLeft = jumpsMax;
	private float jumpSpeed = 7;

	private int currentPowerUp;
	private int powerUpCounter;

	private boolean dashing;
	private int dashCounter;
	private float dashSpeedX, dashSpeedY;

	public Chaser(GameStage stage) {
		super(stage);
		setSprite(Sprites.chaser);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(32, 32);
	}

	public void collideBlock(Block block) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(block.bounds) && block.isCreated()) {
			if (dashing) {
				block.setDead();
			} else {
				if (getY() >= block.getTop()) {
					setY(block.getTop());
					groundedBuffer = true;
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
		}
		setBounds();
	}

	public void collidePowerUp(PowerUp powerUp) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(powerUp.bounds)) {
			currentPowerUp = powerUp.getType();
			powerUpCounter = 300;
			powerUp.setDead();
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 1, 1, 1);
		super.draw(batch, parentAlpha);
	}

	public void act(float delta) {
		grounded = groundedBuffer;
		setX(getX() - Consts.ScreenSpeed);
		super.act(delta);
	}

	public void update() {
		if (getX() < 0) {
			setX(0);
		}
		if (getY() <= 0) {
			ySpeed = 0;
			jumpsLeft = jumpsMax;
			setY(0);
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
			} else {
				powerUpCounter--;
			}
			if (currentPowerUp == 1) {

			} else if (currentPowerUp == 2) {
				jumpSpeed = 10f;
			}
		}

		grounded = getY() <= 0;
		groundedBuffer = false;

		if (Input.KeyDown(Input.Z)
				&& !dashing
				&& (Input.KeyDown(Input.ARROW_UP)
						|| Input.KeyDown(Input.ARROW_DOWN)
						|| Input.KeyDown(Input.ARROW_LEFT) || Input
							.KeyDown(Input.ARROW_RIGHT))) {
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
				xSpeed -= dashSpeedX;
				if (!grounded) {
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
}
