package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class Chaser extends GameObject {
	private boolean grounded;

	public Chaser(GameStage stage) {
		super(stage);
		setSprite(Sprites.chaser);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(32, 32);
	}

	public void act(float delta) {
		super.act(delta);
		grounded = getY() <= 0;
		if (grounded) {
			ySpeed = 0;
			setY(0);
			if (Input.KeyPressed(Input.ARROW_UP)) {
				ySpeed = 7;
			}
		} else {
			ySpeed -= Consts.Gravity;
		}
		xSpeed = 0.5f;
	}

	public void update() {}
}
