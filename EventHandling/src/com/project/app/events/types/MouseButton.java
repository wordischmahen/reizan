package com.project.app.events.types;

import com.project.app.events.Event;

public class MouseButton extends Event {
	
	private int keyCode, x, y;
	
	protected MouseButton(Type type, int keyCode, int x, int y) {
		super(type);
		this.keyCode = keyCode;
		this.y = y;
		this.x = x;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	
}
