package com.dvdfu.gijam.handlers;

import com.badlogic.gdx.math.Rectangle;

public class Bound extends Rectangle {
	private static final long serialVersionUID = 1L;

	public float getTop() {
		return y + height;
	}

	public float getRight() {
		return x + width;
	}

	public boolean bottomOf(Bound other) {
		return getTop() > other.y && y < other.y;
	}

	public boolean topOf(Bound other) {
		return getTop() > other.getTop() && y < other.getTop();
	}

	public boolean leftOf(Bound other) {
		return getRight() > other.x && x < other.x;
	}

	public boolean rightOf(Bound other) {
		return getRight() > other.getRight() && x < other.getRight();
	}
}
