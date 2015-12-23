import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {
	public int width;
	public int heigth;
	
	public Tile[][] tiles;
	
	
	public Level(String path){
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
					}
				}
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	
		
	
		
	}	
	public void render(Graphics g){
		for (int x=0; x<width;x++)
			for (int y=0; y<heigth;y++){
				 if(tiles[x][y] !=null)
					 tiles[x][y].render(g);
			} 
			
	}

}
