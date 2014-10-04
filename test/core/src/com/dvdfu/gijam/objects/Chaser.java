package com.dvdfu.gijam.objects;

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
	private int counter;

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
		xSpeed = 0.5f;

		if (dashing && counter > 0) {
			if (counter > 20) {
				Consts.GameSpeed -= 1f;
			} else if (counter <= 10) {
				Consts.GameSpeed += 1f;
			}
			counter -= 1;
		} else if (dashing && counter == 0) {
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
				counter = 30;
			}
			if (Input.KeyDown(Input.ARROW_LEFT)) {
				xSpeed -= 0.5f;
			}
		}
	}

	public void update() {
	}
}
