package Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import KeyBoardInput.Sound;
import KeyBoardInput.SwitchAction;
import LoadMap.Load;
import LoadMap.MapManager;
import main.MainGame;

public class Player extends Entity {
	
	public int state_ani;
	public int frame1;
	public float xPos;
	public int updateBigMap;
	public static int PlayerX_RightPos;
	public static int PlayerX_LeftPos;
	public static int PlayerY_UpPos;
	public static boolean PlayerGetHit;
	public static int Player_AttackRange;
	

	private int f;

	private int[][] levelData;
    private BufferedImage img;
    private BufferedImage img_healthBar;
    private BufferedImage[][] Animation;
    private boolean left, right, attack, jump;
    private int i = 0;

	private float airSpeed = 0f;
	private float gravity = 0.09f * MainGame.SCALE;
	private float jumpSpeed = -3.4f * MainGame.SCALE;
	private boolean inAir = false;
	public boolean checkStandingLeft;
	public int doubleJump = 0;
	
	
	private boolean acJumpSound = true;
	private Rectangle2D.Float attackRange;
    private Sound sound;
    
	private int healthWidth = (int) (246*MainGame.SCALE);


    
	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		loadAni();
		sound = new Sound();
		levelData = MapManager.levelData_Player;
		attackRange = new Rectangle2D.Float(x, y, width, height);
	}
	
	public void updatePlayer() {
		if(i >= frame1) i = 0;
		f = i++;
	}

	
	private void updateState() {
		createHitbox(x+35, y+7, width-70, height-22);
		setAttackRange();
		
		if(SwitchAction.action == 0) state_ani = 0;
		else if (SwitchAction.action == 1)state_ani = 1;
		   
		
		
		if (jump) {
			if(acJumpSound) { getSound();}  
			acJumpSound = false;
			jump(); 
			
		}
	

		if (left && !right) {
			x -= 2.2;
			state_ani = 3;
		} else if (right && !left) {
			x += 2.2;
			state_ani = 2;
		}
		

		
	

	
		if(attack) { 
			if( (right && !left) || state_ani == 0)
			  state_ani = 6;
			else if((left && !right) || state_ani == 1)
			  state_ani = 7;
		
				
		}

		
		if (!inAir)
			if (!CheckHitBox.IsEntityOnFloor(hitbox, levelData))
				inAir = true; 

		if (inAir) {
			if (CheckHitBox.CanMoveHere(hitbox.x, hitbox.y + airSpeed, width-70, height-22, levelData)) {
				y += airSpeed;
				airSpeed += gravity;
				acJumpSound = false;
			
			}else {
				airSpeed += gravity + 0.12;
				if (airSpeed > 0) inAir = false; airSpeed = 0; acJumpSound = true;
			}
		}
		

		if(!CheckHitBox.CanMoveHere(x+35, y+7, width-70, height-22,levelData)) {
			if(right) x -= 2.2;
			if(left) x += 2.2;
		}
		

		
	}
	
	private void checkAttackingToEnemy() {
	  if(attack) {	
		 if(f == 2 || f == 5) 
			 Enemy.checkGetHitFromPlayer = true; 
		 else 
			 Enemy.checkGetHitFromPlayer = false;
			
	  }else 	
		  Enemy.checkGetHitFromPlayer = false;

	}

	private void setAttackRange() {
		
	   if((right && !left) || state_ani == 0 || state_ani == 2)
		   attackRange.setRect((x + 65), y + 20, width - 67, height - 35);		
	   else if((left && !right) || state_ani == 1|| state_ani == 3)
		   attackRange.setRect(x + 25, y + 20, width - 67, height - 35);

	}
	


	
	private void jump() {	
		 if (inAir) 
			 return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	public void renderPlayer(Graphics g) {
		updateState();
		getPlayerPosition();
		drawAttackRange(g);
		drawHeathBar(g);
		
		if(PlayerGetHit) { 
			y -=3;
			updateHealth();
		}
		checkAttackingToEnemy();
        g.drawImage(Animation[state_ani][f],(int) (x) - updateBigMap, (int) (y), (int)width, (int)height, null);
        PlayerGetHit  = false;

	}
	
	
	
	public void updateHealth() {
		healthWidth -= 0.25;
		if(healthWidth < 1)
			System.out.println("Die");
			
	}
	
	
	
	private void drawHeathBar(Graphics g) {
		g.drawImage(img_healthBar,0, 0, (int)(img_healthBar.getWidth()*MainGame.SCALE), (int)(img_healthBar.getHeight()*MainGame.SCALE), null);
		g.setColor(Color.red);
		g.fillRect(51, 21, healthWidth ,(int) (4*MainGame.SCALE));
	}

	
	
	private void drawAttackRange(Graphics g) {
		Player_AttackRange = (int) attackRange.x  - updateBigMap;
	}
	
	

	private void getPlayerPosition() {
		xPos = x + 35;
		hitbox.x += updateBigMap;
		PlayerX_RightPos = (int) (x + 35 - updateBigMap);
		PlayerX_LeftPos = (int) (x - updateBigMap);
		PlayerY_UpPos = (int) (y - 3);
	}
	

	private void loadAni() {
		InputStream is = getClass().getResourceAsStream("/player_sprites_edit.png");
		img_healthBar = Load.LoadImage(Load.HEALTH_BAR);
		try {
			img = ImageIO.read(is);
			Animation = new BufferedImage[9][6];
			
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < Animation[i].length; j++) {
					Animation[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
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
	
    
	private void getSound()  {
		try {
			sound.getSound(0, -40);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRight(boolean right2) {
		this.right = right2;
	}
	
	public void setLeft(boolean left2) {
		this.left = left2;
	}
	


	public void setAttack(boolean attack2) {
		this.attack = attack2;
	}
	
	public void setJump(boolean jump2) {
		this.jump = jump2;
	}
	


}
