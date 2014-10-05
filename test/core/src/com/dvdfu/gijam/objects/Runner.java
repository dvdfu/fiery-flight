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

	private float moveMax = 6;
	private float moveSpeed = 0.5f;

	private float jumpSpeed = 10;

	private boolean dashing;
	private int moveCounter = -5;
	private float dashSpeedX, dashSpeedY;
	
	private boolean wonGame;

	public Runner(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(48, 48);
		wonGame = false;
	}

	public void collideBlock(Block block) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(block.bounds) && block.isCreated()) {
			block.setDead(true);
		}
	}

	public void collideChaser(Chaser chaser) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(chaser.bounds)) {
			if (chaser.isDashing()) {
				wonGame = true;
			}
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
		if (gameTime == 900) {
			minX = 700;
			maxX = Consts.ScreenWidth - 100;
		}

		if (!moving && moveCounter == -5) {
			if (getX() < minX) {
				moveLeft = false;
				moveRight = true;
			} else if (getX() > maxX) {
				moveLeft = true;
				moveRight = false;
			} else {
				moveRight = false;
				moveRight = false;
			}
			if (getY() < minY) {
				moveUp = true;
				moveDown = false;
			} else if (getY() > maxY) {
				moveUp = false;
				moveDown = true;
			} else {
				moveUp = false;
				moveDown = false;
			}
			moveCounter = 20;
		}

		if (!dashing) {
			ySpeed -= Consts.Gravity;
		}

		if (moveCounter > 0) {
			if (!moving && MathUtils.random(0, 3) == 1) {
				xSpeed = 0;
				ySpeed = 0;
				dashing = true;
				moving = true;
				if (moveUp) {
					dashSpeedY = 1f;
				} else if (moveDown) {
					dashSpeedY = -1f;
				} else {
					dashSpeedY = MathUtils.random(-1, 1);
				}
				if (moveRight) {
					dashSpeedX = 1f;
				} else if (moveLeft) {
					dashSpeedX = -1f;
				} else {
					dashSpeedX = MathUtils.random(-1, 1);
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
			} else {
				if (!moving) {
					if (moveUp || (!moveDown && MathUtils.random(0, 2) == 1)) {
						ySpeed = jumpSpeed;
					}
					moving = true;
				}
				if (moveRight || (!moveLeft && MathUtils.random(0, 3) == 1)) {
					if (xSpeed < moveMax) {
						xSpeed += moveSpeed;
					} else
						xSpeed = moveMax;
				} else if (xSpeed > 0) {
					xSpeed -= moveSpeed;
				}
				if (moveLeft || (!moveRight && MathUtils.random(0, 1) == 1)) {
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
		moveCounter--;
		if (moveCounter == 0) {
			moving = false;
			dashing = false;
		}
	}
	
	public boolean wonGame() {
		return wonGame;
	}
}
