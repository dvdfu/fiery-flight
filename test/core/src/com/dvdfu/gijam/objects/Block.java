package com.dvdfu.gijam.objects;

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
		xSpeed = Consts.GameSpeed;
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

	public void update() {
	}

	public void createBlock() {
		created = true;
	}
	
	public boolean isDead() {
		return dead;
	}
}
