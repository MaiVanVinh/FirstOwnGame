package Character;



import java.util.ArrayList;
import main.MainGame;

public class Crab_Enemy extends Entity {
	
	public static final int CRABBY_WIDTH_DEFAULT = 72;
	public static final int CRABBY_HEIGHT_DEFAULT = 32;
	public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * MainGame.SCALE);
	public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * MainGame.SCALE);

	ArrayList<Crab_Enemy> list = new ArrayList<>();
	public   static int Offset;
	private  int enemyDir = 1;
	private  float enemySpeed;
	private  boolean inAir = false;
	private  boolean firstUpdate = true;
	private  float airSpeed = 0f;
	private  float gravity = 0.09f * MainGame.SCALE;
	
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
			
			if(enemyDir == 1) 
				enemySpeed = (float) -2;
			else
				enemySpeed = (float) 2;
			
			if (CheckHitBox.CanMoveHere(x + 30 + enemySpeed ,y ,  CRABBY_WIDTH - 57, CRABBY_HEIGHT-10, levelData)) {
				if(CheckHitBox.CheckEgde(x + 30 + enemySpeed ,y ,  CRABBY_WIDTH - 57, CRABBY_HEIGHT-10, levelData))
				   x += enemySpeed;
				else 
				   changeDir();
			}else {
				changeDir();
			}
			
		}
		
	}

	private void changeDir() {
		if(enemyDir == 1)
			enemyDir = 0;
		else
			enemyDir = 1;
		
	}

	
	


}
