package com.dvdfu.gijam.visuals;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animation {
	private Sprite[] frames;

	public Animation(Sprite reg, int width, int height) {
		frames = new Sprite[(int) (reg.getWidth()) / width];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Sprite(reg, i * width, 0, width, height);
		}
	}

	public Sprite getFrame(int frame) {
		while (frame < 0) {
			frame += frames.length;
		}
		return frames[frame % frames.length];
	}

	public int getWidth() {
		return frames[0].getRegionWidth();
	}

	public int getHeight() {
		return frames[0].getRegionHeight();
	}
	
	public int getLength() {
		return frames.length;
	}
}