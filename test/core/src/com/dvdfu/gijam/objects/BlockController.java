package com.dvdfu.gijam.objects;

import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.handlers.Input;

public class BlockController extends GameObject {
	private Block currentBlock;
	
	
	public BlockController(GameStage stage) {
		super(stage);
		this.stage = stage;
	}
	
	public void act() {
		if (Input.MousePressed() && currentBlock == null) {
			currentBlock = new Block(stage);
		}
		if (Input.MouseDown() && currentBlock != null) {
			
		}
		if (Input.MouseReleased() && currentBlock != null) {
			currentBlock.createBlock();
			currentBlock = null;
		}
	}

	public void reset() {}

	public void update() {}
}
