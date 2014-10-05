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
			if (bounds.getY() <= block.getTop() && ySpeed < 0) {
				ySpeed = 0;
				jumpsLeft = jumpsMax;
				setY(block.getTop());
			} else if (bounds.getX() <= block.getRight() && xSpeed < 0) {
				xSpeed = 0;
				setX(block.getRight() + block.getXSpeed());
			} else if (bounds.getRight() >= block.getX() && xSpeed >= 0) {
				xSpeed = 0;
				setX(block.getX() - getWidth() + block.getXSpeed());
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
		ySpeed -= Consts.Gravity;

		if (getY() <= 0) {
			ySpeed = 0;
			jumpsLeft = jumpsMax;
			setY(0);
		}

		if (Input.KeyPressed(Input.ARROW_UP) && jumpsLeft > 0) {
			ySpeed = jumpHeight;
			jumpsLeft--;
		}

		if (Input.KeyDown(Input.ARROW_RIGHT)) {
			xSpeed += 0.5f;
		}
		if (Input.KeyDown(Input.ARROW_LEFT)) {
			xSpeed -= 0.5f;
		}
	}
}
