import java.awt.*;

public class Platform {
	private Rectangle hitBox;
	private boolean isDestroyed = false;
			
	public Platform(int x, int y, int width , int height){
		hitBox = new Rectangle(x,y,width,height);
				
	}
	public void destroy(){
		isDestroyed = true;
	}
	
	public boolean isDestroyed(){
		return this.isDestroyed;
	}
	
	public boolean collidesWidth(Rectangle object){
		return (isDestroyed)? false:hitBox.intersects(object);
	}
		
	
	public void render(Graphics g){
		if(!isDestroyed){
		g.setColor(new Color(0,0,255));
		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		g.setColor(new Color(0,0,0));
		g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		}
	}

	
	
}
