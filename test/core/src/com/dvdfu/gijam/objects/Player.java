package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Player extends GameObject {

	public Player(GameStage stage) {
		super(stage);
		setSprite(Sprites.player);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(32, 32);
	}

	public void act(float delta) {
	}

	public void update() {}
}
