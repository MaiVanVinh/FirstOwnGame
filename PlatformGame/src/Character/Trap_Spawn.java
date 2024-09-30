package Character;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import LoadMap.Load;

public class Trap_Spawn {

	private BufferedImage img;
	private ArrayList<Traps> list;
	
	public int Offset;
	
	public Trap_Spawn() {
		list = new ArrayList<>();
		list = Load.Gettraps();
		load();
		
		
	}
	

	public void update() {
		for (Traps trap : list) {
			   trap.update(Offset);
		}	
		
	}
	
	public void renderTraps(Graphics g) {
		update();
		for(Traps trap : list) {		
			 g.drawImage(img, (int)(trap.x  - Offset),(int) trap.y, 48, 48, null);
		} 
		
	
	}
	
	private void load(){
		InputStream is = getClass().getResourceAsStream("/Trap.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
