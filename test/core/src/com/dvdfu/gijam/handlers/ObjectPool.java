package com.dvdfu.gijam.handlers;

import com.badlogic.gdx.utils.Pool;
import com.dvdfu.gijam.objects.Block;
import com.dvdfu.gijam.objects.GameObject;
import com.dvdfu.gijam.objects.Particle;
import com.dvdfu.gijam.objects.PowerUp;

public class ObjectPool {
	Pool<Block> blocks;
	Pool<PowerUp> powerUps;
	Pool<Particle> particles;

	public ObjectPool(final GameStage stage) {
		blocks = new Pool<Block>() {
			protected Block newObject() {
				return new Block(stage);
			}
		};
		powerUps = new Pool<PowerUp>() {
			protected PowerUp newObject() {
				return new PowerUp(stage);
			}
		};
		particles = new Pool<Particle>() {
			protected Particle newObject() {
				return new Particle(stage);
			}
		};
	}

	public Block getBlock() {
		return blocks.obtain();
	}

	public PowerUp getPowerUp() {
		return powerUps.obtain();
	}

	public Particle getParticle() {
		return particles.obtain();
	}

	public void free(GameObject object) {
		if (object instanceof Block) {
			blocks.free((Block) object);
		} else if (object instanceof PowerUp) {
			powerUps.free((PowerUp) object);
		} else if (object instanceof Particle) {
			particles.free((Particle) object);
		}
	}
}
