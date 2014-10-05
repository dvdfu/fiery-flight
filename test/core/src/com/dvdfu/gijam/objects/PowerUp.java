package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class PowerUp extends GameObject {
	private boolean dead;
	private int type;

	public PowerUp(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.blank, 16, 16);
		reset();
	}

	public void reset() {
		dead = false;
		type = MathUtils.random(1, 2);
		setSize(20, 20);
		setPosition(Consts.ScreenWidth,
				MathUtils.random(10, 400));
	}

	public void act(float delta) {
		super.act(delta);
		xSpeed = -3f;
		if (getRight() < 0) {
			dead = true;
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		if (type == 1) {
			batch.setColor(0, 1, 0, 1);
		}
		else if (type == 2) {
			batch.setColor(0, 0, 1, 1);
		}
		super.draw(batch, parentAlpha);
	}

	public void update() {
		bounds.setPosition(getX() + xSpeed, getY() + ySpeed);
	}

	public int getType() {
		return type;
	}

	public void setDead() {
		dead = true;
	}

	public boolean isDead() {
		return dead;
	}
}
