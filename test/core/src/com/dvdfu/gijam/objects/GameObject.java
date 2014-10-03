package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.dvdfu.gijam.handlers.Bound;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Animation;

public abstract class GameObject extends Actor implements Poolable {
	protected GameStage stage;
	protected Animation sprite;
	private int spriteLength;
	private float spriteTimer;
	private float spriteSpeed;
	protected Bound bounds;
	protected boolean stretched;
	protected boolean facingRight;
	protected int xSprOffset;
	protected int ySprOffset;
	protected float xSpeed;
	protected float ySpeed;

	public GameObject(GameStage stage) {
		super();
		this.stage = stage;
		bounds = new Bound();
		spriteSpeed = 10;
	}

	public void act(float delta) {
		if (spriteSpeed != 0) {
			spriteTimer += 1f / spriteSpeed;
		}
		while (spriteTimer >= spriteLength) {
			spriteTimer -= spriteLength;
		}
		super.setPosition(getX() + xSpeed, getY() + ySpeed);
		super.act(delta);
		setBounds();
	}

	public void draw(Batch batch, float parentAlpha) {
		if (sprite != null) {
			int xSpr = (int) (getX() + 0.5f);
			int ySpr = (int) (getY() + 0.5f);
			if (stretched) {
				batch.draw(sprite.getFrame((int) spriteTimer), xSpr, ySpr,
						getWidth(), getHeight());
			} else {
				batch.draw(sprite.getFrame((int) spriteTimer), xSpr, ySpr,
						sprite.getWidth() / 2, sprite.getHeight() / 2, sprite
								.getWidth(), sprite.getHeight(),
						facingRight ? -1 : 1, 1, 0);
			}
		}
	}

	public abstract void update();

	public void setSprite(Sprite sprite, int width, int height) {
		setSprite(new Animation(sprite, width, height));
	}

	public void setSprite(Animation sprite) {
		if (this.sprite == sprite) {
			return;
		}
		this.sprite = sprite;
		spriteLength = sprite.getLength();
		resetSprite();
	}

	public void setSpriteSpeed(float speed) {
		spriteSpeed = speed;
	}

	public void setVelocity(float xSpeed, float ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public float getCX() {
		return getX() + getWidth() / 2;
	}

	public float getCY() {
		return getY() + getHeight() / 2;
	}

	public void resetSprite() {
		spriteTimer = 0;
	}

	public float getXSpeed() {
		return xSpeed;
	}

	public float getYSpeed() {
		return ySpeed;
	}

	public void setBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public Bound getBounds() {
		return bounds;
	}
}
