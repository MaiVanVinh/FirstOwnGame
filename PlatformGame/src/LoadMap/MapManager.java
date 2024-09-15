package LoadMap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.MainGame;

public class MapManager {
	private BufferedImage image;
	private BufferedImage[] index;
	private Map level;
	private MainGame game;
	public static int[][] levelData_Player;
	
	public MapManager(MainGame game){
		this.game = game;
		get_Tile_Block();
		level = new Map(Load.GetMapLevelData());
		levelData_Player = Load.GetMapLevelData();
	}
	
	public void get_Tile_Block() {
		game.warning();
		image = Load.LoadImage(Load.TILE_2D);
		index = new BufferedImage[48];
		
		int value = 0;
		
		  for(int i = 0; i < 4; i++) {
			  for(int j = 0; j < 12; j++ ) {
				  index[value] = image.getSubimage(j*32, i*32, 32, 32);
				  value++; 
				 
			  }
		  }value = 0;
	}
	
	public void get_Map_Level(Graphics g) {
		for (int j = 0; j < MainGame.TOTAL_TILES_IN_HEIGHT; j++)
			for (int i = 0; i < MainGame.TOTAL_TILES_IN_WIDTH; i++) {
				int num= level.get_levelData_index(i, j);
				g.drawImage(index[num], MainGame.TILES_SIZE * i, MainGame.TILES_SIZE * j, MainGame.TILES_SIZE, MainGame.TILES_SIZE, null);
			}
		
		
	}
	

	
	
}
