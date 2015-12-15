package com.kyrsach.main;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Timer;

import com.kyrsach.display.Display;

public class Main {

	public static void main(String[] args) {
		
	Display.created(800, 600, "Tanks",0xff00ff00, 3);	
     
    Timer t = new Timer(100 / 60, new AbstractAction() { //перерисовка 60 кадров в секунду 
    	
    	public void actionPerformed(ActionEvent e ) {
    		Display.clear();
    		Display.render();
    		
    		Display.swapBuffers();//что бы было видно , то что мы создали на экране и то что видим
    	
    	}
    	
   	});
    	
    
      
       t.setRepeats(true);
       t.start();
       
	}
	
}
       
  
     

