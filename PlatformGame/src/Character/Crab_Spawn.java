package Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import LoadMap.Load;
import LoadMap.MapManager;
import main.MainGame;

public class Crab_Spawn {

	private BufferedImage[][] Animation;
	private BufferedImage img;
	private int i = 0;
	private int frame;
	public int Offset;

	public static final int CRABBY_WIDTH_DEFAULT = 72;
	public static final int CRABBY_HEIGHT_DEFAULT = 32;
	public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * MainGame.SCALE);
	public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * MainGame.SCALE);

	ArrayList<Crab_Enemy> numberOfCrabs = new ArrayList<>();
   
	

	public Crab_Spawn() {
		addCrabs();
		loadEnemy();
	}
	
	
	private void addCrabs() {
		numberOfCrabs = Load.GetCrabs();
	}
	
	public void updateCrabState(){
		if(i > 6) i = 0;
		frame = i++;
	}
    
	public void update(int [][] levelData) {
		for (Crab_Enemy crab : numberOfCrabs)
			crab.checkHitBox_withEnv(levelData);
	}

	
	public void renderCrabs(Graphics g) {
		Crab_Enemy.Offset = Offset;
		update(MapManager.levelData_Player);
		for(Crab_Enemy crab : numberOfCrabs) {
			g.drawImage(Animation[0][frame], (int)crab.x - Offset,(int)crab.y , CRABBY_WIDTH, CRABBY_HEIGHT, null);
			g.setColor(Color.BLACK);
			g.drawRect((int)crab.x - Offset + 30 ,(int)crab.y + 5  , CRABBY_WIDTH - 57, CRABBY_HEIGHT-10);
		}
	}

	private void loadEnemy() {
		InputStream is = getClass().getResourceAsStream("/Enemy1.png");

		try {
			img = ImageIO.read(is);
			Animation = new BufferedImage[5][9];
			
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < Animation[i].length; j++) {
					Animation[i][j] = img.getSubimage(j * 72, i * 32, 72, 32);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
				try {
					is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
		
	}
	

	
}
