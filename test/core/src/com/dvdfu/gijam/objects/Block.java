package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Block extends GameObject {
	private boolean created;
	private int createTimer;
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
		setX(getX() - Consts.ScreenSpeed);
		if (created) {
			if (createTimer > 0) {
				createTimer--;
			}
			if (getRight() < 0) {
				dead = true;
			}
		}
		if (getY() > 0) {
			if (created && createTimer == 0) {
				ySpeed -= Consts.Gravity;
			}
		} else {
			setY(0);
			ySpeed = 0;
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		if (created) {
			batch.setColor(1, 1, 1, 1);
		} else {
			batch.setColor(1, 1, 1, 0.5f);
		}

		int size = 16;
		batch.draw(Sprites.blockTL, getX(), getTop() - size);
		batch.draw(Sprites.blockTR, getRight() - size, getTop() - size);
		batch.draw(Sprites.blockBL, getX(), getY());
		batch.draw(Sprites.blockBR, getRight() - size, getY());
		batch.draw(Sprites.blockC, getX(), getY() + size, size, getHeight()
				- size * 2);
		batch.draw(Sprites.blockC, getRight() - size, getY() + size, size,
				getHeight() - size * 2);
		batch.draw(Sprites.blockC, getX() + size, getY(),
				getWidth() - size * 2, getHeight());
	}

	public void update() {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
	}

	public boolean isCreated() {
		return created;
	}

	public void createBlock() {
		created = true;
		createTimer = 60;
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

	public void setDead() {
		dead = true;
	}

	public boolean isDead() {
		return dead;
	}
}
