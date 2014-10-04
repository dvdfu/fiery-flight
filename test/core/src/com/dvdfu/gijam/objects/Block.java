package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Block extends GameObject {
	private boolean created;
	private boolean dead;

	public Block(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
	}

	public void reset() {
		created = false;
		dead = false;
	}

	public void act(float delta) {
		super.act(delta);
		if (created) {
			if (getY() <= 0) {
				ySpeed = 0;
				setY(0);
			}
			else {
				ySpeed -= Consts.Gravity;
			}
			if (getRight() < 0) {
				dead = true;
			}
		}
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 0, 0, 1);
		super.draw(batch, parentAlpha);
	}

	public void update() {
	}

	public void createBlock() {
		created = true;
		xSpeed = -1.5f;
	}
	
	public void collideBlock(Block block) {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
		if (bounds.overlaps(block.bounds)) {
			System.out.println(ySpeed + " " + xSpeed);
			if (bounds.getY() < block.getTop() && ySpeed < 0) {
				ySpeed = 0;
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
	
	public boolean isDead() {
		return dead;
	}
}
