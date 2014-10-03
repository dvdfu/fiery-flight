package com.dvdfu.gijam.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputController extends InputAdapter {

	public boolean mouseMoved(int x, int y) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		return true;
	}

	public boolean touchDragged(int x, int y, int pointer) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		Input.mouseClick = true;
		return true;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		Input.mouseClick = true;
		return true;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		Input.mouseClick = false;
		return true;
	}

	public boolean keyDown(int k) {
		Input.setKey(Input.ANY_KEY, true);
		switch (k) {
		case Keys.UP:
			Input.setKey(Input.ARROW_UP, true);
			break;
		case Keys.DOWN:
			Input.setKey(Input.ARROW_DOWN, true);
			break;
		case Keys.LEFT:
			Input.setKey(Input.ARROW_LEFT, true);
			break;
		case Keys.RIGHT:
			Input.setKey(Input.ARROW_RIGHT, true);
			break;
		case Keys.Z:
			Input.setKey(Input.Z, true);
			break;
		case Keys.X:
			Input.setKey(Input.CTRL, true);
			break;
		case Keys.CONTROL_LEFT:
			Input.setKey(Input.CTRL, true);
			break;
		}
		return true;
	}

	public boolean keyUp(int k) {
		Input.setKey(Input.ANY_KEY, false);
		switch (k) {
		case Keys.UP:
			Input.setKey(Input.ARROW_UP, false);
			break;
		case Keys.DOWN:
			Input.setKey(Input.ARROW_DOWN, false);
			break;
		case Keys.LEFT:
			Input.setKey(Input.ARROW_LEFT, false);
			break;
		case Keys.RIGHT:
			Input.setKey(Input.ARROW_RIGHT, false);
			break;
		case Keys.Z:
			Input.setKey(Input.Z, false);
			break;
		case Keys.X:
			Input.setKey(Input.CTRL, false);
			break;
		case Keys.CONTROL_LEFT:
			Input.setKey(Input.CTRL, false);
			break;
		}
		return true;
	}
}