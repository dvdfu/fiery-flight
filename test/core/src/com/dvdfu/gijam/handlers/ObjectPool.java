//package com.dvdfu.gijam.handlers;
//
//import com.badlogic.gdx.utils.Pool;
//
//public class ObjectPool {
//
//	public ObjectPool(final GameStage stage) {
//		solid = new Pool<Floor>() {
//			protected Floor newObject() {
//				return new Floor(stage);
//			}
//		};
//	}
//
//	public Floor getSolid() {
//		return solid.obtain();
//	}
//
//	public void free(GameObject object) {
//		if (object instanceof Floor) {
//			solid.free((Floor) object);
//		}
//	}
//}
