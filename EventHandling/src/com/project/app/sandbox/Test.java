package com.project.app.sandbox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import com.project.app.events.Dispatcher;
import com.project.app.events.Event;
import com.project.app.events.types.MouseMotion;
import com.project.app.events.types.MousePressed;
import com.project.app.events.types.MouseReleased;
import com.project.app.layers.Layer;

public class Test extends Layer {
	
	
	private Color color;
	private Rectangle rect;
	private boolean dragging = false;
	private int px, py;
	
	private static final Random random = new Random();
	
	public Test(Color color) {
		
		this.color = color;
		
		rect = new Rectangle(random.nextInt(100) + 150, random.nextInt(100) + 250, 120, 240);
		
	}


	public void onEvent(Event event) {
		  Dispatcher dispatcher = new Dispatcher(event);
		  dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMoved((MouseMotion)e));
		  dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onPressed((MousePressed) e));
	      dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) ->  onReleased((MouseReleased) e));
	  }
	  
	  public void onRender(Graphics g) {
		  g.setColor(color);
		  g.fillRect(rect.x, rect.y, rect.width, rect.height);
		  
	  }
	  private boolean onPressed(MousePressed event) {
			if (rect.contains(new Point(event.getX(), event.getY())))
				dragging = true;
			return dragging;
			
			
		}
		private boolean onReleased(MouseReleased event) {
			dragging = false;
			return dragging;
		}
		private boolean onMoved(MouseMotion event) {
			if(dragging) {
				rect.x += event.getX() - px;
				rect.y += event.getY() - py;
			}
			px = event.getX();
			py = event.getY();		
			return dragging;
		}
	}
