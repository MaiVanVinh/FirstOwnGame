package LoadMap;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Character.Crab_Enemy;
import main.MainGame;


public class Load {

	
	public static final String MAP_LEVEL_ONE    = "Map1.png";
	public static final String MAP_LEVEL_TWO    = "Map2.png";
	public static final String MAP_LEVEL_THREE  = "Map3.png";
	public static final String MAP_LEVEL_FOUR   = "Map4.png";
	public static final String MAP_LEVEL_FIVE   = "Map5.png";
	public static final String MAP_LEVEL_TEST   = "TestMap.png";
	public static final String TILE_2D = "2D Tiles.png";
	
	
	
	public static BufferedImage LoadImage(String name) {
		
		BufferedImage image = null;
		InputStream is = Load.class.getResourceAsStream("/" + name);
		
		try {
			image = ImageIO.read(is);
				
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
				try {
					is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}

		return image;
		
	}
	
	
	public static ArrayList<Crab_Enemy> GetCrabs(){
		ArrayList<Crab_Enemy> list = new ArrayList<>();	
		BufferedImage img = LoadImage(MAP_LEVEL_TEST);
		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				
				if (value == 0) {
                   list.add(new Crab_Enemy(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE - 40));
				}
			}		
		}
		
		return list;
	}
	
	public static int[][] GetMapLevelData() {
		
		BufferedImage img = LoadImage(MAP_LEVEL_TEST );
		int[][] levelData = new int[img.getHeight()][img.getWidth()];

        
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				
				if (value >= 48)
                    value = 0;
				levelData[j][i] = value;
			}
		
		}
		
		return levelData;

	}
	
	

}
