package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.dvdfu.gijam.handlers.Enums.ParticleType;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Particle extends GameObject {
	private ParticleType type;
	private int timer;

	public Particle(GameStage stage) {
		super(stage);
		reset();
		setSprite(Sprites.blockC, 16, 16);
		type = ParticleType.ROCK;
		stretched = true;
	}

	public boolean dead() {
		return timer <= 0;
	}

	public void setType(ParticleType type) {
		this.type = type;
		switch (type) {
		case ROCK:
			setSprite(Sprites.blockC, 16, 16);
			setSize(32, 32);
			timer = 16;
			ySpeed = MathUtils.random(-1f, 1f);
			xSpeed = MathUtils.random(-1f, 1f);
			break;
		case FIRE:
			setSprite(Sprites.blank, 16, 16);
			timer = 48;
			ySpeed = MathUtils.random(2f);
			xSpeed = MathUtils.random(-1f, 1f);
			break;
		default:
			break;
		}
		reset();
	}

	public void act(float delta) {
		switch (type) {
		case ROCK:
			setSize(getWidth() - 1, getHeight() - 1);
			break;
		case FIRE:
			setSize(timer / 2, timer / 2);
			break;
		}
		timer--;
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		switch (type) {
		case FIRE:
			if (timer > 24) {
				batch.setColor(1, (timer - 24) / 24f, 0, 1);
			} else {
				batch.setColor(0.4f, 0.4f, 0.4f, timer / 24f);
			}
		default:
			super.draw(batch, parentAlpha);
			break;
		}
	}

	public void reset() {
		resetSprite();
		setOrigin(getWidth() / 2, getHeight() / 2);
	}

	public void update() {
	}

	public void setPosition(float x, float y) {
		setX(x - getWidth() / 2);
		setY(y - getHeight() / 2);
	}

}
