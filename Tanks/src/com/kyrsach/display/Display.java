package com.kyrsach.display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public abstract class Display {
	
	private static boolean created = false; 
	public static JFrame window;
	private static Canvas content;
	
	
	public static void create(int width, int heigth, String title){
		if (created)
		return;
		
		window = new JFrame(title);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new Canvas();
		
		//задаю размер окну 
		
		Dimension size = new Dimension ( width, heigth);
		content.setPreferredSize(size);
		
		window.setResizable(false);
		window.getContentPane().add(content);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
	}

}
