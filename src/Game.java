import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel{
    
	private Dimension gameField = new Dimension(600,500);
	private boolean isRunning = false;
	private boolean isPoused = false;
	private Platform[][] platforms;
	private boolean won = false;
	private boolean lost =false;
	

	private int ballCount;
	
	private Player player;
	private Ball ball;
	
	public Game(Frame conteiner, int platformOnX, int platformOnY){
		
	conteiner.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if ( won || lost){
					if(e.getKeyCode() == KeyEvent.VK_ENTER) System.exit(0);
				}else if  (!isRunning  || isPoused  ){
				    if(e.getKeyCode() == KeyEvent.VK_ENTER) start();
				
	 
				
				} else {
					if(e.getKeyCode() == KeyEvent.VK_RIGHT ) player.moveOnXAxis(15);
					if(e.getKeyCode() == KeyEvent.VK_LEFT ) player.moveOnXAxis(-15);
				
				
					
					
				}
				
			}
		
		});
	
	 platforms = new Platform[platformOnX][platformOnY];
	 for (int x = 0; x != platforms.length;x++){	
		 for (int y = 0; y != platforms[0].length;y++){
			 int pWidth = gameField.width/platformOnX;
			 int pHeight = (gameField.height/4)/platformOnY;
			 platforms[x][y] = new Platform(x*pWidth, y*pHeight, pWidth, pHeight);
		 }
	 }
		
	 
	   player = new Player(this, (int) ((gameField.getWidth()-Player.standartPlayerWidth)/2), gameField.height-Player.standartPlayerWidth, Player.standartPlayerWidth, Player.standartPlayerHeight);
       ball = new Ball(this, gameField.width/2, gameField.height/2, Ball.standartBallRadius);
       ballCount =  3;
	}
	
	
	 
	public void loseBall(){
		pouse();
		ballCount-=1;
		if(ballCount<=0) lost=true;
		ball.SetVector(5,5);
		ball.setPosition(gameField.width/2, gameField.height/2);
		player.setX((int) ((gameField.getWidth()-Player.standartPlayerWidth)/2));
		player.setY( gameField.height-Player.standartPlayerWidth);
		repaint();
	}
	
	public void playerWon() {
		won= true;
	}
	
	public void start() {
		isPoused=false;
	    if(!isRunning)	gameThread.start();
		
	}
	
	@SuppressWarnings("deprecation")
	public void pouse() {
		isPoused=true;	
		
		
	}
	
	public void stop(){
		isRunning= false;
	}
	 	
	 	
	public Dimension getGameDimension(){
		return gameField;
	}
	
	public void setPlayer(Player player){
		
		this.player = player;
	}
	
	
	public Player getPlayer(){
	   return this.player;
	   
	}
	
	public Platform[][] getPlatforms(){
		return this.platforms;
	}
	
	
public void setSize(Dimension size){
	super.setSize(size);
	if(!isRunning ){
		
	
	gameField = new Dimension(size.width-200,size.height-200);	
	for (int x = 0; x != platforms.length;x++){	
		 for (int y = 0; y != platforms[0].length;y++){
			 int pWidth = gameField.width/platforms.length;
			 int pHeight = (gameField.height/4)/platforms[0].length;
			 platforms[x][y] = new Platform(x*pWidth, y*pHeight, pWidth, pHeight);
		 }
	ball.setPosition(gameField.width/2, gameField.height/2);
	player.setX((int) ((gameField.getWidth()-Player.standartPlayerWidth)/2));
	player.setY( gameField.height-Player.standartPlayerWidth);
	}
	
	}
	
}
	 	
     private Thread gameThread = new Thread(new Runnable (){
    	 public void run(){
    		 isRunning = true;
    		 ball.SetVector(10, 10);
    		 while(isRunning){
    			 if(!isPoused){
    			
    				 
    				 won=true;
    				 for (Platform[] pls : platforms){ 
    						for (Platform p : pls){
    							if(!p.isDestroyed()) won = false;
    						}
    					}
    				  
    			 ball.tick();
    			 repaint();
    			 try {
					Thread.sleep(30);
				} catch (Exception e){}
    			 
				}
    		 }
    	 }
     });

	public void paint(Graphics g){
		super.paint(g);
		g.setColor(new Color(255,0,0));
	    g.translate((getWidth()-gameField.width)/2,(getHeight()-gameField.height)/2);
	    
	    for (int i=0 ; i !=ballCount;i++){
	    	int radius = 6;
	    	g.fillOval(i*radius*2,-(radius*2+3), radius*2, radius*2);
	    }
	
		
	    g.setColor(new Color(255,255,255,100));
		g.fillRect(0, 0, gameField.width, gameField.height);
		
		
		ball.render(g);
		player.render(g);
		
		for (Platform[] pls : platforms){ 
			for (Platform p : pls){
				p.render(g);
			}
		}
		
		g.setColor(new Color(0,0,0) );
		g.drawRect(0, 0, gameField.width, gameField.height);
		
		
		if (won){
			g.drawString("U WON",325, 150);
			stop();
		}
		
		if(lost){
			g.drawString("U LOSE", 325, 150);
		    stop();
		}
		
		g.drawString("Press ENTER to start the game",260,-15);
	}
	
}
