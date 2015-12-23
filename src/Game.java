import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable  {
	private boolean isRunning = false;
	public static final int WIDTH=480,HEIGTH=400;
	public static final String Title = "Pac-man";
	
	
	private Thread thread;
	
	public Game(){
		Dimension dimension = new Dimension(Game.WIDTH,Game.HEIGTH);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
	}
	
	
	public synchronized void start (){
		 if (isRunning) return;
		 isRunning = true;
		 thread = new Thread(this);
		 thread.start();
		
	}
	
	public synchronized void stop(){
		  if (isRunning)return;
		  isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}

		
	}
	
	
	private void tick(){
		//System.out.println("Working");
		
	}
	
	private void render(){
	   BufferStrategy bs = getBufferStrategy();
	   if(bs == null){
		   createBufferStrategy(3);
		   return;
		   
	   }
	   
	   Graphics g = bs.getDrawGraphics();
	   g.setColor(Color.black);
	   g.fillRect(0, 0, Game.WIDTH, Game.HEIGTH);
	   
	   g.dispose();
	   bs.show();
	}
	
	
	
	@Override
	public void run() {
		int fps=0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 60.0;
		double delta = 0;
		double ns =  1000000000/targetTick;
		
		 while(isRunning){
			 
			 long now = System.nanoTime();
			 delta+=(now-lastTime)/ns;
			 lastTime = now;
			 
			 while (delta >=1){
				 tick();
				 render();
				 fps++;
				 delta--;
			 }
		
		if (System.currentTimeMillis() - timer >= 1000)	 {
			System.out.println(fps);
			fps = 0;
			timer+=1000;
		}
			 
		 }
		
		stop();
	}

	public static void main(String[]args){
		
		Game game = new Game();
		JFrame frame = new JFrame(Game.Title);
		frame.setTitle(Game.Title);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		game.start();
		
	}
	
}