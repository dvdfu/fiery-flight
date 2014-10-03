package com.dvdfu.gijam.visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Sprites {
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/panic.pack"));
	public static final Animation player = new Animation(atlas.createSprite("player_idle_r"), 16, 16);
	public static final Animation playerRunR = new Animation(atlas.createSprite("player_run_r"), 16, 16);
	public static final Animation playerRunL = new Animation(atlas.createSprite("player_run_l"), 16, 16);
	public static final Animation playerIdleR = new Animation(atlas.createSprite("player_idle_r"), 16, 16);
	public static final Animation playerIdleL = new Animation(atlas.createSprite("player_idle_l"), 16, 16);
	public static final Animation playerJumpR = new Animation(atlas.createSprite("player_jump_r"), 16, 16);
	public static final Animation playerJumpL = new Animation(atlas.createSprite("player_jump_l"), 16, 16);
	public static final Animation plain = new Animation(atlas.createSprite("plain"), 32, 32);
	public static final Animation star = new Animation(atlas.createSprite("star"), 10, 10);
	public static final Animation pop = new Animation(atlas.createSprite("pop"), 6, 6);
	public static final Animation enemyWalk = new Animation(atlas.createSprite("enemy_walk"), 24, 24);
	public static final Animation enemyRock = new Animation(atlas.createSprite("enemy_rock"), 24, 24);
	public static final Animation fireB = new Animation(atlas.createSprite("fire_big"), 8, 8);
	public static final Animation fireS = new Animation(atlas.createSprite("fire_small"), 4, 4);
}
