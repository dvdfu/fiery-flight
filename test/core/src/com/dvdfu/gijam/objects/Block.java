package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.GameStage;

public class Block extends GameObject {
	private boolean created;

	public Block(GameStage stage) {
		super(stage);
		
	}

	public void reset() {
		
	}
	
	public void act(float delta) {
		super.act(delta);
		if (created) {
			ySpeed -= 0.5f;
		} else {
			
		}
		
	}

	public void update() {}
	
	public void createBlock() {
		created = true;
	}
}
