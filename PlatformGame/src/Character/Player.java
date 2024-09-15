package Character;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import KeyBoardInput.SwitchAction;

public class Player extends Entity {
	
	
	public int state_ani;
	public int frame1;


	
	private int f;
    private BufferedImage img;
    private BufferedImage[][] Animation;
    private boolean left, up, right, down, attack;
    private int i = 0;
    


	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		loadAni();
		
	}
	public void updatePlayer() {
		if(i >= frame1) i = 0;
		f = i++;
	}

	
	private void updateState() {

		if(SwitchAction.action < 1) state_ani = 0;
			

		if (left && !right) {
			x -= 2;
			state_ani = 1;
		} else if (right && !left) {
			x += 2;
			state_ani = 1;
		}

		if (up && !down) {
			y -= 2;
			state_ani = 1;
		} else if (down && !up) {
			y += 2;
			state_ani = 1;
		}
		
		if(attack) state_ani = 6;  
		
	}


	
	
	public void renderPlayer(Graphics g) {
		updateState();
		g.drawImage(Animation[state_ani][f],(int) x, (int) y, (int)width, (int)height, null);
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
	
	public void setUp(boolean up2) {
		this.up = up2;
	}
	
	public void setDown(boolean down2) {
		this.down = down2;
	}

	public void setAttack(boolean attack2) {
		this.attack = attack2;
	}

	
	
	
	

}
