package com.project.app;

import java.awt.Color;

import com.project.app.core.Window;
import com.project.app.sandbox.Test;

public class Main {

	public static void main(String args[]) {
		Window window = new Window("Display", 960, 640);
		window.addLayer(new Test(Color.getHSBColor(32, 57, 120)));
		window.addLayer(new Test(Color.getHSBColor(15, 3, 90)));
	}
}
