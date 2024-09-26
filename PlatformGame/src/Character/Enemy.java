package Character;



import main.MainGame;

public class Enemy extends Entity {
	
	public static int EnemyX_LeftPos;
	public   static int Offset;
	private  int enemyDir = 1;
	private  float enemySpeed = 1;
	private  boolean inAir = false;
	private  boolean firstUpdate = true;
	private  float airSpeed = 0f;
	private  float gravity = 0.09f * MainGame.SCALE;
	public static boolean checkGetHitFromPlayer = false;
	protected boolean active = true; 
	protected int enemyState = 1; 
	long lastCheck = System.currentTimeMillis();

	
	public Enemy(float x, float y, float width, float height) {
		super(x, y, width, height);
		createHitbox(x - Offset ,y , width, height);
	}
	
	
	
	protected void checkHitBox_withEnv(int [][] levelData) {
		
		if (firstUpdate) {
			if (!CheckHitBox.IsEntityOnFloor(hitbox, levelData))
				inAir = true;
			firstUpdate = false;
		}
		
		if (inAir) {
			if (CheckHitBox.CanMoveHere(x,y + airSpeed,  width, height, levelData)) {
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
			
			EnemyX_LeftPos = (int) (x + 30 - Offset);
			
			
	        if(checkGetHitFromPlayer) 
	            checkGettingHit();  
	           
	        
			if (CheckHitBox.CanMoveHere(x + 30  + enemySpeed ,y , width, height, levelData)) {
				if(CheckHitBox.CheckEgde(x + 30  + enemySpeed ,y , width, height, levelData)) {
					if(checkInRange()) 
				    	 turnTowardsPlayer();
				    x += enemySpeed;
				    return;		
				}
			}		
            changeDir();
            active = true;


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
		if (Player.PlayerX_RightPos > EnemyX_LeftPos) 
			enemySpeed = (float) 1; // move to right
		else 
			enemySpeed = (float) -1;
		
	}


	
	
	private boolean checkInRange() {
		   
	        // Calculate distance between player and enemy
	        int dx = (int) ((Player.PlayerX_LeftPos) - EnemyX_LeftPos);
	        int dy = (int) (Player.PlayerY_UpPos - y);
	        if(Math.abs(dy) >= 102)
	        	return false;

            if(dx > -45 && dx < 0 && Math.abs(dy) < 25)
            	Player.PlayerGetHit = true;

           
	        if(Player.PlayerX_RightPos > EnemyX_LeftPos)
			   return Math.abs(dx) < 65;
	        else
	           return Math.abs(dx) < 120;
	    }
	
	private void checkGettingHit() {
		int dx = (int) ((Player.Player_AttackRange) - EnemyX_LeftPos);
		if(dx > -25 && dx < 44) {
			active = false;
		}
	}

	
	public boolean isActive() {
		return active;
	}
	
	public int getEnemyState() {
		return enemyState;
	}
	


}
