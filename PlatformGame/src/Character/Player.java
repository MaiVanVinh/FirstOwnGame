package Character;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import KeyBoardInput.SwitchAction;
import LoadMap.MapManager;
import main.MainGame;

public class Player extends Entity {
	
	public int state_ani;
	public int frame1;

	private int f;

	private int[][] levelData;
    private BufferedImage img;
    private BufferedImage[][] Animation;
    private boolean left, right, attack, jump;
    private int i = 0;

	private float airSpeed = 0f;
	private float gravity = 0.09f * MainGame.SCALE;
	private float jumpSpeed = -3.5f * MainGame.SCALE;
	private boolean inAir = false;


	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		loadAni();
		levelData = MapManager.levelData_Player;
	}
	
	public void updatePlayer() {
		if(i >= frame1) i = 0;
		f = i++;
	}

	
	private void updateState() {
		createHitbox(x+35, y+7, width-70, height-22);
		//createHitbox(x+25, y+5, width-50, height-15);
		
		if(SwitchAction.action < 1) state_ani = 0;
			
		if (jump) jump();
		

		
	
		
		if (left && !right) {
			x -= 2.2;
			state_ani = 1;
		} else if (right && !left) {
			x += 2.2;
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
				
			}else {
				airSpeed += gravity + 0.12;
				if (airSpeed > 0) inAir = false; airSpeed = 0;
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
		g.drawImage(Animation[state_ani][f],(int) (x), (int) (y), (int)width, (int)height, null);
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
