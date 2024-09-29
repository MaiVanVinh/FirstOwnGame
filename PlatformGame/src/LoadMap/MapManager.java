package LoadMap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


import javax.imageio.ImageIO;

import main.MainGame;

public class MapManager {
	
	 private BufferedImage image;
	 private BufferedImage[] index;
	 private Map level;
	 private MainGame game;
	

	 private BufferedImage imageBackGround = null;
	 private Image scaledImage;

	 public int xLvlOffset;
	 private int diff;
	 private int leftBorder = (int) (0.3   * MainGame.GAME_WIDTH);
	 private int rightBorder = (int) (0.7 * MainGame.GAME_WIDTH);
	 private int lvlTilesWide;    //= Load.GetMapLevelData()[0].length;
	 private int maxTilesOffset; //= lvlTilesWide - MainGame.TOTAL_TILES_IN_WIDTH;
	 private int maxLvlOffsetX; //= maxTilesOffset * MainGame.TILES_SIZE;
	 public float player_xPos;
	 public static int[][] levelData_Player;
	
	public MapManager(MainGame game){
		this.game = game;
		get_Tile_Block();
//		level = new Map(Load.GetMapLevelData());
//		levelData_Player = Load.GetMapLevelData();
		iniMapAndOffset();

		InputStream is = getClass().getResourceAsStream("/bg3.png");
		
	   
	    	try {
				imageBackGround = ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	       
	    scaledImage = imageBackGround.getScaledInstance(1248, 672, Image.SCALE_SMOOTH); 
	}
	
	
	private void iniMapAndOffset() {
		 lvlTilesWide = Load.GetMapLevelData()[0].length;
		 maxTilesOffset = lvlTilesWide - MainGame.TOTAL_TILES_IN_WIDTH;
		 maxLvlOffsetX = maxTilesOffset * MainGame.TILES_SIZE;
		 level = new Map(Load.GetMapLevelData());
		 levelData_Player = Load.GetMapLevelData();
		 System.out.println(Load.mapControler.get(Load.index));
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
	
	public void checkMoveLeftAndRight() {
		int playerX = (int) player_xPos;
		diff = playerX - xLvlOffset;

		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;
		
		
	}
	
	
	
	public void get_Map_Level(Graphics g) {
		checkMoveLeftAndRight();
		

		g.drawImage(scaledImage, 0, 0, 1248, 672, null);
		
		for (int j = 0; j < MainGame.TOTAL_TILES_IN_HEIGHT; j++)
			for (int i = 0; i < levelData_Player[0].length; i++) {
				int num = level.get_levelData_index(i, j);	
				g.drawImage(index[num], MainGame.TILES_SIZE * i - xLvlOffset, MainGame.TILES_SIZE * j , MainGame.TILES_SIZE, MainGame.TILES_SIZE, null);
			}
		
		
	}
	

	
	
}
