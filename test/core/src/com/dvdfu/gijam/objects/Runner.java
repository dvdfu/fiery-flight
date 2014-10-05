package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Runner extends GameObject {
	private int gameTime = 0;
	
	private float minX = Consts.ScreenWidth + 1000;
	private float minY = 200;
	private float maxX = Consts.ScreenWidth + 1000;
	private float maxY = Consts.ScreenHeight - 200;
	
	private boolean moving, moveLeft, moveRight, moveUp, moveDown;
	
	private float moveMax = 5;
	private float moveSpeed = 0.3f;

	private float jumpSpeed = 7;

	private boolean dashing;
	private int moveCounter;
	private float dashSpeedX, dashSpeedY;

	public Runner(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(48, 48);
	}

	public void collideBlock(Block block) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(block.bounds) && block.isCreated()) {
			block.setDead(true);
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 1, 1, 1);
		super.draw(batch, parentAlpha);
	}

	public void act(float delta) {
		setX(getX() - Consts.ScreenSpeed);
		super.act(delta);
	}

	public void update() {
		move();
		gameTime++;
	}

	public void move() {
		if (gameTime == 100) {
			minX = 600;
			maxX = Consts.ScreenWidth - 200;
		}
		
		if (!moving) {
			if (getX() < minX) {
				moveLeft = false;
				moveRight = true;
			} else if (getX() > maxX) {
				moveLeft = true;
				moveRight = false;
			}
			else {
				moveRight = false;
				moveRight = false;
			}
			if (getY() < minY) {
				moveUp = true;
				moveDown = false;
			} else if (getY() > maxY) {
				moveUp = false;
				moveDown = true;
			}
			else {
				moveUp = false;
				moveDown = false;
			}
		}
		
		if (!dashing) {
			ySpeed -= Consts.Gravity;
		}

		if (!moving && MathUtils.random(0, 1) == 1) {
			xSpeed = 0;
			ySpeed = 0;
			dashing = true;
			moving = true;
			moveCounter = 20;
			if (moveUp) {
				dashSpeedY = 2f;
			} else if (moveDown) {
				dashSpeedY = -2f;
			} else {
				dashSpeedY = MathUtils.random(-2, 2);
			}
			if (moveRight) {
				dashSpeedX = 2f;
			} else if (moveLeft) {
				dashSpeedX = -2f;
			} else {
				dashSpeedX = MathUtils.random(-2, 2);
			}
		}

		if (dashing) {
			if (moveCounter > 10) {
				xSpeed += dashSpeedX;
				ySpeed += dashSpeedY;
			} else if (moveCounter <= 8) {
				xSpeed -= dashSpeedX;
				if (getY() > 0) {
					ySpeed -= dashSpeedY;
				}
			}
			moveCounter--;
			if (moveCounter == 0) {
				moving = false;
				dashing = false;
			}
		} else {
			if (!moving) {
				if (moveUp || (!moveDown && MathUtils.random(0, 1) == 1)) {
					ySpeed = jumpSpeed;
				}
				moveCounter = 20;
				moving = true;
			}
			if (moveRight) {
				if (xSpeed < moveMax) {
					xSpeed += moveSpeed;
				} else
					xSpeed = moveMax;
			} else if (xSpeed > 0) {
				xSpeed -= moveSpeed;
			}
			if (moveLeft) {
				if (xSpeed > -moveMax) {
					xSpeed -= moveSpeed;
				} else
					xSpeed = -moveMax;
			} else if (xSpeed < 0) {
				xSpeed += moveSpeed;
			}
			moveCounter--;
			if (moveCounter == 0) {
				moving = false;
			}
		}
	}
}
