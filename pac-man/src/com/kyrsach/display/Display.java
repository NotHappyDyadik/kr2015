package com.kyrsach.display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;

public abstract class Display {
	
	private static boolean created = false; 
	public static JFrame window;
	private static Canvas content;
	
    private static BufferedImage buffer;	
    private static int [] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;

	
	
	
	public static void create(int width, int heigth, String title, int _clearColor){
		if (created)
		return;
		
		window = new JFrame(title);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new Canvas();
			
	
		Dimension size = new Dimension ( width, heigth);
		content.setPreferredSize(size);
	
		
		window.setResizable(false);
		window.getContentPane().add(content);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		buffer = new BufferedImage(width,heigth,BufferedImage.TYPE_INT_ARGB);
		bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
		bufferGraphics = buffer.getGraphics();
		

		created = true;
	}

	public static void clear() {
		Arrays.fill(bufferData,clearColor);
	}

   public static void render(){
	   
   }
   public static void swapBuffers() {
		Graphics g = content.getGraphics();
		g.drawImage(buffer, 0, 0, null);
	
	}
	static public void pacmanIsOn() {
		if(Display.foodIsOn(curCoord)) {
			SCORES--;
			if(SCORES == 0) {
				running = false;
				win = true;
				return;
			}
			Display.delFoodAt(curCoord);
		}
	}
   
}
