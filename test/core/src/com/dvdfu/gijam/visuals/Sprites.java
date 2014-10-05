package com.dvdfu.gijam.visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Sprites {
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img_out/atlas.pack"));
	public static final Animation chaser = new Animation(atlas.createSprite("player_run_r"), 16, 16);
	public static final Sprite blank = atlas.createSprite("blank");
	public static final Sprite blockC = atlas.createSprite("block-main");
	public static final Sprite blockTL = atlas.createSprite("block-tl");
	public static final Sprite blockTR = atlas.createSprite("block-tr");
	public static final Sprite blockBL = atlas.createSprite("block-bl");
	public static final Sprite blockBR = atlas.createSprite("block-br");
	public static final Sprite bgMountain = atlas.createSprite("mountains");
	public static final Sprite menu = atlas.createSprite("menu");
}