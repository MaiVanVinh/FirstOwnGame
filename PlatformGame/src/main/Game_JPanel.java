package main;


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;




public class Game_JPanel extends JPanel{

	private static final long serialVersionUID = 1L;


    private MainGame game;

	public Game_JPanel(MainGame game) {
		this.game = game;
		setPanelSize();

     }
	

	private void setPanelSize() {
		Dimension size = new Dimension(MainGame.GAME_WIDTH, MainGame.GAME_HEIGHT);
		System.out.println(MainGame.GAME_WIDTH);
		System.out.println( MainGame.GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
        game.render(g);
		
	}

	
	
    public MainGame getGame() {
	 return game;
   }


   


}
