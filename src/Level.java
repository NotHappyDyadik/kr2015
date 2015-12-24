import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Level {
	public int width;
	public int heigth;
	
	public Tile[][] tiles;
	public List<Apple> apples;
	public List<Enemy> enemies;
	
	
	public Level(String path){
		apples = new ArrayList<>();
		enemies = new ArrayList<>();
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.heigth = map.getHeight();
			int[] pixels = new int[width*heigth];
			tiles = new Tile[width][heigth];
			map.getRGB(0, 0, width, heigth, pixels,0,width);
			for(int xx = 0; xx < width; xx++){
				for (int yy=0; yy < heigth; yy++){
					int val = pixels[xx+(yy*width)];
					
					if(val == 0xff000000){
						tiles[xx][yy] = new Tile(xx*32,yy*32);
					}else if (val == 0xff00ff){
						Game.player.x=xx*32;
						Game.player.y=yy*32;
					}else if(val==0xffff0000){
						enemies.add(new Enemy(xx*32,yy*32));
					}else{
						apples.add(new Apple(xx*32,yy*32));
					}
				}
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}	
	

	public void tick(){
		for (int i=0; i < enemies.size();i++){
		enemies.get(i).tick();
		}
	}
	public void render(Graphics g){
		for (int x=0; x<width;x++){
			for (int y=0; y<heigth;y++){
				 if(tiles[x][y] !=null)
					 tiles[x][y].render(g);
			} 
			
	}
     for (int i=0; i < apples.size();i++){
    	 apples.get(i).render(g);
     }

	for (int i=0; i < enemies.size();i++){
		enemies.get(i).render(g);
	}
	
}}
