package com.dvdfu.gijam.handlers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dvdfu.gijam.visuals.Sprites;

public class GameStage extends Stage {
	private Vector3 camPosition;
	private float camSmoothing;
	private float camShake;

	public GameStage() {
		super();
		camPosition = new Vector3();
		camSmoothing = 0.05f;
	}

	private void updateCam() {
		float camX = getCamera().position.x + (camPosition.x - getCamera().position.x) * camSmoothing;
		float camY = getCamera().position.y + (camPosition.y - getCamera().position.y) * camSmoothing;
		if (camShake > 0) {
			camX += MathUtils.random(-1f, 1f) * camShake;
			camY += MathUtils.random(-1f, 1f) * camShake;
			camShake--;
		}
		setCamPosition(camX, camY);
	}

	public void act(float delta) {
		updateCam();
		super.act(delta);
	}

	public void setCamFocus(float x, float y) {
		camPosition.set(x, y, 0);
	}

	public void setCamPosition(float x, float y) {
		camPosition.set(x, y, 0);
		getCamera().position.set(x, y, 0);
	}

	public void setCamSmooth(float smooth) {
		camSmoothing = smooth;
	}

	public void setCamShake(float shake) {
		camShake = shake;
	}

	public float getCamX() {
		return getCamera().position.x;
	}

	public float getCamY() {
		return getCamera().position.y;
	}

	public void draw() {
		getSpriteBatch().begin();
		getSpriteBatch().setColor(80f / 255, 23f / 255, 23f / 255, 1);
		getSpriteBatch().draw(Sprites.plain.getFrame(0), getCamX() - Consts.ScreenWidth / 2,
			getCamY() - Consts.ScreenHeight / 2, Consts.ScreenWidth, Consts.ScreenHeight);
		getSpriteBatch().end();
		super.draw();
	}
}
