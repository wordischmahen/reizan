package com.project.app.events.types;

import com.project.app.events.Event;

public class MousePressed extends MouseButton {

	public MousePressed(int keyCode, int x, int y) {
		
		super(Event.Type.MOUSE_PRESSED, keyCode, x, y);
	}

}
