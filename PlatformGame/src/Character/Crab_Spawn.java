package Character;

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
	
	ArrayList<Crab> numberOfCrabs = new ArrayList<>();
   
	

	public Crab_Spawn() {
		addCrabs();
		loadEnemy();
	}
	
	
	private void addCrabs() {
		numberOfCrabs = Load.GetCrabs();
	}
	
	public void updateCrabState(){
		if(i > 5) i = 0;
		frame = i++;
	}
    
	public void update(int [][] levelData) {
		for (Crab crab : numberOfCrabs) {
			if(crab.isActive()) {
			   crab.updateMove(levelData);
			}
		}	
		
	}

	
	public void renderCrabs(Graphics g) {
		Enemy.Offset = Offset;
		update(MapManager.levelData_Player);
		for(Crab crab : numberOfCrabs) {		
			if(crab.isActive()) { 
			   crab.deadAnimationTick = 0;
			   g.drawImage(Animation[crab.getEnemyState()][frame], (int)crab.x - Offset,(int)crab.y , CRABBY_WIDTH, CRABBY_HEIGHT, null);

			}else { 
				if(crab.getDeadAniTick() < 200)
			      g.drawImage(Animation[4][frame], (int)crab.x - Offset,(int)crab.y , CRABBY_WIDTH, CRABBY_HEIGHT, null);
				crab.deadAnimationTick++; 
			}

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
