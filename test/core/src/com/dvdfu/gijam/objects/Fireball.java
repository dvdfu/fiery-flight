package com.dvdfu.gijam.objects;

import com.badlogic.gdx.math.MathUtils;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Fireball extends GameObject {
	private float targetX;
	private float targetY;

	public Fireball(GameStage stage) {
		super(stage);
		setSprite(Sprites.blank, 32, 32);
		stretched = true;
		setSize(0, 0);
	}

	@Override
	public void reset() {
	}
	
	public void act(float delta) {
		float theta = MathUtils.atan2(targetY - getY(), targetX - getX());
		xSpeed = MathUtils.cos(theta) * 10;
		ySpeed = MathUtils.sin(theta) * 10;
		super.act(delta);
	}

	public void update() {
		
	}
	
	public void target(float x, float y) {
		targetX = x;
		targetY = y;
	}
	
	public boolean isDead() {
		float dx = getX() - targetX;
		float dy = getY() - targetY;
		return dx * dx + dy * dy < 100;
	}
	
}
