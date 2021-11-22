package com.map;

import com.hs.ConstGame;

public class Coordinates {

	public static int pixelToTile(double i) {
		return (int)(i / ConstGame.InfoObject.SCALED_SIZE);
	}

	public static double pixelToTile1(double i) {
		return (i / ConstGame.InfoObject.SCALED_SIZE);
	}

	public static int tileToPixel(int i) {
		return i * ConstGame.InfoObject.SCALED_SIZE;
	}
	
	public static int tileToPixel(double i) {
		return (int)(i * ConstGame.InfoObject.SCALED_SIZE);
	}
	
	
}
