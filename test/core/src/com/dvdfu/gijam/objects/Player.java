package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.GameStage;

public class Player extends GameObject {

	public Player(GameStage stage) {
		super(stage);
		reset();
	}

	public void reset() {
		setPosition(0, 0);
		setSize(32, 32);
	}

	public void update() {
		System.out.println("aa");
	}
}
