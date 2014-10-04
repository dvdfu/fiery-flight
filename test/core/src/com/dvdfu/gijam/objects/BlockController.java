package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;
import com.dvdfu.gijam.visuals.Sprites;

public class BlockController extends GameObject {
	private Block currentBlock;
	private float origX;
	private float origY;

	public BlockController(GameStage stage) {
		super(stage);
		this.stage = stage;
		setSprite(Sprites.chaser);
		stretched = true;
		setSize(0, 0);
		currentBlock = null;
	}

	public void act() {
		if (Input.MousePressed() && currentBlock == null) {
			origX = Input.mouse.x;
			origY = Input.mouse.y;
			currentBlock = new Block(stage);
		} else if (Input.MouseDown() && currentBlock != null) {

		} else if (Input.MouseReleased() && currentBlock != null) {
			if (Math.abs(Input.mouse.x - origX)
					* Math.abs(Input.mouse.y - origY) > 500f) {
				currentBlock.setPosition(Math.min(Input.mouse.x, origX),
						Math.min(Input.mouse.y, origY));
				currentBlock.setSize(Math.abs(Input.mouse.x - origX),
						Math.abs(Input.mouse.y - origY));
				stage.addActor(currentBlock);
				currentBlock.createBlock();
			}
			currentBlock = null;

		}
	}

	public void reset() {
	}

	public void update() {
	}
}
