package LoadMap;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Character.Crab;
import Character.Traps;
import main.MainGame;


public class Load {

	
	public static final String MAP_LEVEL_ONE    = "Map1.png";
	public static final String MAP_LEVEL_TWO    = "getstuck.png";
	public static final String MAP_LEVEL_TEST   = "MapTest.png";

	public static final String TILE_2D = "2D Tiles.png";
	public static final String HEALTH_BAR = "health_bar2.png";
	public static ArrayList<String> mapControler;
	public static int index = 0;
	
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
	
	public static void mapControler() {
		mapControler = new ArrayList<>();
		mapControler.add(MAP_LEVEL_ONE);
		mapControler.add(MAP_LEVEL_TWO);
		mapControler.add(MAP_LEVEL_TEST);
        
	}
	
	public static ArrayList<Crab> GetCrabs(){
		ArrayList<Crab> list = new ArrayList<>();	
		BufferedImage img = LoadImage(mapControler.get(index));
		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				
				if (value == 0) 
                   list.add(new Crab(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));
				
			}		
		}

		return list;
	}
	
	
	public static ArrayList<Traps> Gettraps(){
		ArrayList<Traps> trap = new ArrayList<>();	
		BufferedImage img = LoadImage(mapControler.get(index));

		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == 0) {
					trap.add(new Traps(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));
				}
			}		
		}
        System.out.println(trap.size());
		return trap;
	}
	
	public static int[][] GetMapLevelData() {
		
		BufferedImage img = LoadImage(mapControler.get(0));
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
