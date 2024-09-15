package LoadMap;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.MainGame;

public class Load {

	
	public static final String MAP_LEVEL_ONE = "Map1.png";
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
	
	public static int[][] GetMapLevelData() {
		int[][] levelData = new int[MainGame.TOTAL_TILES_IN_HEIGHT][MainGame.TOTAL_TILES_IN_WIDTH];
		BufferedImage img = LoadImage(MAP_LEVEL_ONE);

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				levelData[j][i] = value;
			}
		return levelData;

	}
	
	

}
