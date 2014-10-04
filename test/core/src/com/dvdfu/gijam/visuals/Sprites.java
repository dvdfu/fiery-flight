package com.dvdfu.gijam.visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Sprites {
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img_out/atlas.pack"));
	public static final Animation player = new Animation(atlas.createSprite("blank"), 32, 32);
}
