package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Block extends GameObject {
	private boolean created;
	private boolean dead;
	private float hitX;
	private float hitY;

	public Block(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
	}

	public void reset() {
		created = false;
		dead = false;
		ySpeed = 0;
	}

	public void act(float delta) {
		super.act(delta);
		setX(getX() - Consts.ScreenSpeed);
		if (created) {
			if (getRight() < 0) {
				dead = true;
			}
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		float trans = created ? 1 : 0.5f;
		batch.setColor(1, 1, 1, trans);

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

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isDead() {
		return dead;
	}
	
	public float hitX() {
		return hitX;
	}
	
	public float hitY() {
		return hitY;
	}
}
