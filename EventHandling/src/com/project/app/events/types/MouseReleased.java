package com.project.app.events.types;

import com.project.app.events.Event;

public class MouseReleased extends MouseButton {

	public MouseReleased(int keyCode, int x, int y) {
		
		super(Event.Type.MOUSE_RELEASED, keyCode, x, y);
	}

}
