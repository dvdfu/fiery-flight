package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class PowerUp extends GameObject {

	public PowerUp(GameStage stage) {
		super(stage);
		setSprite(Sprites.chaser);
		this.setSize(20, 20);
	}

	public void reset() {
	}

	public void act(float delta) {
		super.act(delta);
		xSpeed = -3f;
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 0, 1, 0);
		super.draw(batch, parentAlpha);
	}

	public void update() {
	}

}
