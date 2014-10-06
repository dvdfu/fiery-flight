package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class Gauge extends GameObject{

	public Gauge(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.blank, 32, 32);
		setSize(30,150);
	}

	public void reset() {
			
	}

	public void setHeight(float height)
	{
		setSize(30,height);
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 1, 0, 1);
		super.draw(batch, parentAlpha);
	}
	
	public void update() {
		
	}
	
}
