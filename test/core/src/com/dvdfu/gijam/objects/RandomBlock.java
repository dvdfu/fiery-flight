package com.dvdfu.gijam.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dvdfu.gijam.handlers.GameStage;
import com.dvdfu.gijam.visuals.Sprites;

public class RandomBlock extends Block {
	public RandomBlock(GameStage stage) {
		super(stage);
		stretched = true;
		setSprite(Sprites.chaser);
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1, 0, 0, 1);

		int size = 16;
		batch.draw(Sprites.blockTL, getX(), getTop() - size);
		batch.draw(Sprites.blockTR, getRight() - size, getTop() - size);
		batch.draw(Sprites.blockBL, getX(), getY());
		batch.draw(Sprites.blockBR, getRight() - size, getY());
		batch.draw(Sprites.blockC, getX(), getY() + size, size, getHeight()
				- size * 2);
		batch.draw(Sprites.blockC, getRight() - size, getY() + size, size,
				getHeight() - size * 2);
		batch.draw(Sprites.blockC, getX() + size, getY(),
				getWidth() - size * 2, getHeight());
	}
}
