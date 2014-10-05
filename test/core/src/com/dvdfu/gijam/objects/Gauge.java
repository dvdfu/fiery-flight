package com.dvdfu.gijam.objects;

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
	
	public void update() {
		
	}
	
}
