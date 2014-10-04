package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class Block extends GameObject {
	private boolean created;

	public Block(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
	}

	public void reset() {

	}

	public void act(float delta) {
		super.act(delta);
		if (created) {
			if (this.getY() <= 0) {
				ySpeed = 0;
				setPosition(this.getX(), 0);
			}
			else {
				ySpeed -= 0.5f;
			}
			xSpeed = -1.5f;
		} else {

		}

	}

	public void update() {
	}

	public void createBlock() {
		created = true;
	}
}
