package com.project.app.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.project.app.events.Event;
import com.project.app.events.types.MouseMotion;
import com.project.app.events.types.MousePressed;
import com.project.app.events.types.MouseReleased;
import com.project.app.layers.Layer;

@SuppressWarnings("serial")
public class Window extends Canvas {

	private BufferStrategy bs;
	private Graphics g;
	private JFrame frame;
	private List<Layer> layers = new ArrayList<Layer>();
	public Window(String name, int width, int height) {
		setPreferredSize(new Dimension(width, height));
		init(name);
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				MousePressed event = new MousePressed(e.getButton(),
						e.getX(), e.getY());
			   onEvent(event);
			}
			
			public void mouseReleased(MouseEvent e) {
				MouseReleased event = new MouseReleased( e.getButton(),
						e.getX(), e.getY());
				onEvent(event);
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				MouseMotion event = new MouseMotion(e.getX(),
						e.getY(),false);
				onEvent(event);
			}

			
			public void mouseDragged(MouseEvent e) {
				MouseMotion event = new MouseMotion(e.getX(),
						e.getY(), true);
				onEvent(event);
				
			}
		});
		render();
	}
	
	private void init(String name) {
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
	}
	
	private void render() {
		 if (bs == null)
			 createBufferStrategy(3);
		 bs = getBufferStrategy();
		 
		 g = bs.getDrawGraphics();
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, getWidth(), getHeight());
		 onRender(g);
		 g.dispose();
		 bs.show();
		 
		 try {
			 Thread.sleep(3);
		 } catch (InterruptedException e) {
			 
		 }
		 
		 EventQueue.invokeLater(() -> render());
	}
	
	private void onRender(Graphics g) {
		for (int i = 0; i < layers.size(); i++)
			layers.get(i).onRender(g);
	}
	
	private void onEvent(Event event) {
		for (int i = layers.size() - 1; i >= 0; i--)
			layers.get(i).onRender(g);
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);
	}
}
