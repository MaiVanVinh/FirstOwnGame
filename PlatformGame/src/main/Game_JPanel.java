package main;


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;




public class Game_JPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	//int j  = 0;
	//public int dirNumber;
	//public int state_ani;
   // private int xPos = 100;
    //private int yPos = 100;
   // private BufferedImage img;
    //private BufferedImage[][] Animation;
    private MainGame game;

	public Game_JPanel(MainGame game) {
		this.game = game;
		//importImg();
		//loadAnimation();
		setPanelSize();

     }
	

	private void setPanelSize() {
		Dimension size = new Dimension(800, 400);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}
/*
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");

		try {
			img = ImageIO.read(is);
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
	
	private void loadAnimation() {
		Animation = new BufferedImage[9][6];
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < Animation[i].length; j++) {
				Animation[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
			}
		}

		
	}
	


	public void updateState() {
		if(SwitchAction.action == 1) {
			switch(dirNumber) {
			case 0: yPos -= 3;
			break;
			
			case 1: yPos += 3;
			break;
			
			case 2: xPos -= 3;
			break;
			
			case 3: xPos += 3;
			break;
			}
		}else {
			state_ani = 0;
		}
	}
	*/
	public void paintComponent(Graphics g) {	
		
		super.paintComponent(g);
		//updateState();
        game.render(g);
		//g.drawImage(Animation[state_ani][j], xPos, yPos, 128, 80, null);

	}

	
	
    public MainGame getGame() {
	 return game;
   }


   


}
