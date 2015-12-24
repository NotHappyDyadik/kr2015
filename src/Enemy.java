import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends Rectangle{

	 public Enemy(int x, int y ){
		 setBounds(x,y,32,32);
		 
	 }

	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x+10,y,8,8);
	
	}

	

	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
