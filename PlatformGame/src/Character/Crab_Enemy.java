package Character;



import java.util.ArrayList;
import main.MainGame;

public class Crab_Enemy extends Entity {
	
	public static int CrabX_LeftPos;
	public static int CrabX_RightPos;
	public static int CrabY_HeadPos;
	public static final int CRABBY_WIDTH_DEFAULT = 72;
	public static final int CRABBY_HEIGHT_DEFAULT = 32;
	public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * MainGame.SCALE);
	public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * MainGame.SCALE);

	ArrayList<Crab_Enemy> list = new ArrayList<>();
	public   static int Offset;
	private  int enemyDir = 1;
	private  float enemySpeed = 1;
	private  boolean inAir = false;
	private  boolean firstUpdate = true;
	private  float airSpeed = 0f;
	private  float gravity = 0.09f * MainGame.SCALE;
	long lastCheck = System.currentTimeMillis();

	
	public Crab_Enemy(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT);
		createHitbox(x - Offset + 30 ,y + 5 , CRABBY_WIDTH - 57, CRABBY_HEIGHT-10);
	}
	
	
	
	public void checkHitBox_withEnv(int [][] levelData) {
		
		if (firstUpdate) {
			if (!CheckHitBox.IsEntityOnFloor(hitbox, levelData))
				inAir = true;
			firstUpdate = false;
		}
		
		if (inAir) {
			if (CheckHitBox.CanMoveHere(x - Offset + 30 ,y + airSpeed,  CRABBY_WIDTH - 57, CRABBY_HEIGHT-10, levelData)) {
				y += airSpeed;
				airSpeed += gravity;
			
			}else {
				inAir = false;
			}
		}else {
			
			

			

	
		    
			
			if(enemyDir == 1) // 1 is Left --- 0 is Right
				enemySpeed = (float) -1;
			else
				enemySpeed = (float) 1;
			
		   CrabX_LeftPos = (int) (x - Offset + 30);

			
			if (CheckHitBox.CanMoveHere(x + 30 + enemySpeed ,y ,  CRABBY_WIDTH - 57, CRABBY_HEIGHT-10, levelData)) {
				if(CheckHitBox.CheckEgde(x + 30 + enemySpeed ,y ,  CRABBY_WIDTH - 57, CRABBY_HEIGHT-10, levelData)) {
					if(checkInRange()) 
				    	 turnTowardsPlayer();
				    x += enemySpeed;
				    return;		
				}
			}
			
			
			
            changeDir();
			
			
            checkInRange();
			
			
			
			
		}
		
		
		
		
		
	}

	private void changeDir() {
		if(enemyDir == 1) {
			x += enemySpeed;
			enemyDir = 0;
		}else {
			x -= enemySpeed;
			enemyDir = 1;
		}
	}
	
	private void turnTowardsPlayer() {
		if (Player.PlayerX_RightPos > CrabX_LeftPos) 
			enemySpeed = (float) 1; // move to right
		else 
			enemySpeed = (float) -1;
		
	}


	
	
	private boolean checkInRange() {
		   
	        // Calculate distance between player and enemy
	        int dx = (int) ((Player.PlayerX_LeftPos) - CrabX_LeftPos);
	        int dy = (int) (Player.PlayerY_UpPos - y);
	        if(Math.abs(dy) >= 102)
	        	return false;

            if(dx > -45 && dx < 0 && Math.abs(dy) < 25)
            	Player.PlayerGetHit = true;

           
	        if(Player.PlayerX_RightPos > CrabX_LeftPos)
			   return Math.abs(dx) < 65;
	        else
	           return Math.abs(dx) < 120;
	    }
	
	


}
