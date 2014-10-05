package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.Consts;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Background extends GameObject {
	private float offset1;
	private float offset2;

	public Background(GameStage stage) {
		super(stage);
		setSprite(Sprites.chaser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		offset1 += Consts.GameSpeed / 4f;
		if (offset1 < -Sprites.bgMountain.getWidth()) {
			offset1 = 0;
		}
		offset2 += Consts.GameSpeed / 2f;
		if (offset2 < -Sprites.bgMountain.getWidth()) {
			offset2 = 0;
		}
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(0, 1, 1, 1);
		for (float i = offset1; i < Consts.ScreenWidth; i += Sprites.bgMountain.getWidth()) {
			batch.draw(Sprites.bgMountain, i, 0);
		}
		batch.setColor(0, 1 / 2f, 1, 1);
		for (float i = offset2; i < Consts.ScreenWidth; i += Sprites.bgMountain.getWidth()) {
			batch.draw(Sprites.bgMountain, i, -100);
		}
	}

}
