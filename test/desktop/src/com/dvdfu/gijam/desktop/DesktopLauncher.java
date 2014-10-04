package com.dvdfu.gijam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dvdfu.gijam.MainGame;
import com.dvdfu.gijam.handlers.Consts;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Consts.ScreenWidth;
		config.height = Consts.ScreenHeight;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
