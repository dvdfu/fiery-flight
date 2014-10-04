package com.dvdfu.gijam.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Input {
	public static Vector2 mouse;
	public static boolean mouseClick;
	private static boolean mouseClickPrev;
	public static boolean[] keys;
	private static boolean[] keysPrev;
	public static final int ARROW_UP = 0;
	public static final int ARROW_DOWN = 1;
	public static final int ARROW_LEFT = 2;
	public static final int ARROW_RIGHT = 3;
	public static final int Z = 4;
	public static final int CTRL = 5;
	public static final int ANY_KEY = 6;
	private static final int NUM_KEYS = ANY_KEY + 1;
	static {
		mouse = new Vector2();
		keys = new boolean[NUM_KEYS];
		keysPrev = new boolean[NUM_KEYS];
	}

	public static void update() {
		mouseClickPrev = mouseClick;
		for (int i = 0; i < NUM_KEYS; i++) {
			keysPrev[i] = keys[i];
		}
	}

	public static boolean MouseDown() {
		return mouseClick;
	}

	public static boolean MousePressed() {
		return mouseClick && !mouseClickPrev;
	}

	public static boolean MouseReleased() {
		return !mouseClick && mouseClickPrev;
	}
	
	public static float MouseX() {
		return mouse.x;
	}
	
	public static float MouseY() {
		return Gdx.graphics.getHeight() - mouse.y;
	}

	public static void setKey(int i, boolean b) {
		keys[i] = b;
	}

	public static boolean KeyDown(int i) {
		return keys[i];
	}

	public static boolean KeyPressed(int i) {
		return keys[i] && !keysPrev[i];
	}

	public static boolean KeyReleased(int i) {
		return !keys[i] && keysPrev[i];
	}
}