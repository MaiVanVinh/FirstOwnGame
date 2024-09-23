package Character;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import KeyBoardInput.Sound;
import KeyBoardInput.SwitchAction;
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
	

	private int f;

	private int[][] levelData;
    private BufferedImage img;
    private BufferedImage[][] Animation;
    private boolean left, right,up,down, attack, jump;
    private int i = 0;

	private float airSpeed = 0f;
	private float gravity = 0.09f * MainGame.SCALE;
	private float jumpSpeed = -3.4f * MainGame.SCALE;
	private boolean inAir = false;
	public int doubleJump = 0;
	
	private boolean acJumpSound = true;
    private Sound sound;

    
	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		loadAni();
		sound = new Sound();
		levelData = MapManager.levelData_Player;
	}
	
	public void updatePlayer() {
		if(i >= frame1) i = 0;
		f = i++;
	}

	
	private void updateState() {
		createHitbox(x+35, y+7, width-70, height-22);

		
		if(SwitchAction.action < 1) state_ani = 0;
		
		if (jump) {
			if(acJumpSound) { getSound();}  
			acJumpSound = false;
			jump(); 
			
		}
	


		
//		if (left && !right && !PlayerGetHit) {
//			x -= 2.2;
//			state_ani = 1;
//		} else if (right && !left && !PlayerGetHit) {
//			x += 2.2;
//			state_ani = 1;
//		}
		


    	
		if (left && !right) {
			x -= 2.2;
			state_ani = 2;
		} else if (right && !left) {
			x += 2.2;
			state_ani = 1;
		}
		
    
	  


 
  
  

		if (up && !down) {
			y -= 2.2;
			state_ani = 1;
		} else if (down && !up) {
			y += 2.2;
			state_ani = 1;
		}
		
	

	
		if(attack) state_ani = 6;  
		
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


	
	private void jump() {	
		 if (inAir) 
			 return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	public void renderPlayer(Graphics g) {
		updateState();
		xPos = x + 35;
		hitbox.x += updateBigMap;
		
		
		PlayerX_RightPos = (int) (x + 35 - updateBigMap);
		
		PlayerX_LeftPos = (int) (x - updateBigMap);
		
		PlayerY_UpPos = (int) (y - 3);
		
		if(PlayerGetHit) 
			y -=3;

		
        g.drawImage(Animation[state_ani][f],(int) (x) - updateBigMap, (int) (y), (int)width, (int)height, null);
        PlayerGetHit  = false;

	}
	
	

	
	

	private void loadAni() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");

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
	


	public void setUp(boolean up2) {
		this.up = up2;
	}
	
	public void setDown(boolean down2) {
		this.down = down2;
	}

	
	
	

}
