package com.dvdfu.gijam.handlers;

import com.badlogic.gdx.utils.Pool;
import com.dvdfu.gijam.objects.Block;
import com.dvdfu.gijam.objects.GameObject;

public class ObjectPool {
	Pool<Block> blocks;

	public ObjectPool(final GameStage stage) {
		blocks = new Pool<Block>() {
			protected Block newObject() {
				return new Block(stage);
			}
		};
	}

	public Block getBlock() {
		return blocks.obtain();
	}

	public void free(GameObject object) {
		if (object instanceof Block) {
			blocks.free((Block) object);
		}
	}
}
