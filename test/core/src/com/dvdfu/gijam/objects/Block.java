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
		setBounds();
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
		int size = 16;
		batch.draw(Sprites.blockTL, getX(), getTop() - size);
		batch.draw(Sprites.blockTR, getRight() - size, getTop() - size);
		batch.draw(Sprites.blockBL, getX(), getY());
		batch.draw(Sprites.blockBR, getRight() - size, getY());
		batch.draw(Sprites.blockC, getX(), getY() + size, getWidth(), getHeight() - size * 2);
		batch.draw(Sprites.blockC, getX() + size, getY(), getWidth() - size * 2, getHeight());
	}

	public void update() {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
	}

	public void createBlock() {
		created = true;
		xSpeed = -1.5f;
	}
	
	public void collideBlock(Block block) {
		if (bounds.overlaps(block.bounds) && block.created) {
			if (bounds.getY() < block.getTop() && ySpeed < 0) {
				ySpeed = 0;
				setY(block.getTop());
			}
		}
		setBounds();
	}
	
	public boolean isDead() {
		return dead;
	}
}
