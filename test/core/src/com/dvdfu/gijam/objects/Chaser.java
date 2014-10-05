package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class Chaser extends GameObject {
	private int jumpsMax = 2;
	private int jumpsLeft = jumpsMax;
	private int jumpHeight = 7;
	private boolean grounded;
	private int dashesMax = 1;
	private int dashesLeft = dashesMax;
	private boolean dashing = false;
	private int dashCounter;

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
		if (bounds.overlaps(block.bounds)) {
			System.out.println(ySpeed + " " + xSpeed);
			if (bounds.getY() < block.getTop() && ySpeed < 0) {
				ySpeed = 0;
				jumpsLeft = jumpsMax;
				setY(block.getTop());
			} else if (bounds.getTop() > block.getY() && ySpeed > 0) {
				ySpeed = 0;
				setY(block.getY() - getHeight());
			}
			if (bounds.getX() < block.getRight() && xSpeed < 0) {
				xSpeed = 0;
				setX(block.getRight());
			} else if (bounds.getRight() > block.getX() && xSpeed > 0) {
				xSpeed = 0;
				setX(block.getX() - getWidth());
			}
		}
		setBounds();
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 1, 1, 1);
		super.draw(batch, parentAlpha);
	}

	public void update() {
		xSpeed = 0.5f;

		if (dashing && dashCounter > 0) {
			if (dashCounter > 20) {
				Consts.GameSpeed -= 1f;
			} else if (dashCounter <= 10) {
				Consts.GameSpeed += 1f;
			}
			dashCounter -= 1;
		} else if (dashing && dashCounter == 0) {
			dashing = false;
			Consts.GameSpeed = -1.5f;
		} else {
			grounded = getY() <= 0;
			if (grounded) {
				ySpeed = 0;
				jumpsLeft = jumpsMax;
				dashesLeft = dashesMax;
				setY(0);
			} else {
				ySpeed -= Consts.Gravity;
			}

			if (Input.KeyPressed(Input.ARROW_UP) && jumpsLeft > 0) {
				ySpeed = jumpHeight;
				jumpsLeft--;
			}

			if (Input.KeyDown(Input.ARROW_RIGHT) && dashesLeft > 0) {
				ySpeed = 0;
				dashesLeft--;
				dashing = true;
				dashCounter = 30;
			}
			if (Input.KeyDown(Input.ARROW_LEFT)) {
				xSpeed -= 0.5f;
			}
		}
	}
}
