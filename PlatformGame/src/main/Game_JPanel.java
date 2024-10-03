package main;


import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JPanel;




public class Game_JPanel extends JPanel{

	private static final long serialVersionUID = 1L;
    private MainGame game;
    private PauseMenu pauseMenu;
    private DieMenu dieMenu;
    private PlayingMenu play;
    public static int selectionMenu = 2;
    
    
	public Game_JPanel(MainGame game) {
		this.game = game;	
		
		pauseMenu = new PauseMenu();
		pauseMenu.setVisible(false);
		
		dieMenu = new DieMenu();
		dieMenu.setVisible(false);
		
		play = new PlayingMenu();
		play.setVisible(true);
		

		
        add(pauseMenu);
        add(dieMenu);
        add(play);
		setPanelSize();
		
     }
	

	private void setPanelSize() {
		Dimension size = new Dimension(MainGame.GAME_WIDTH, MainGame.GAME_HEIGHT);
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
    
    public void pauseMenu() {
    	if(!pauseMenu.isVisible() && selectionMenu == 0)
    		pauseMenu.setVisible(true);
    	else if(pauseMenu.isVisible() && selectionMenu == 0)
    		pauseMenu.setVisible(false);
    }
    
    public void dieMenu() {
    	if(selectionMenu == 1)
    		dieMenu.setVisible(true);
    	else
    		dieMenu.setVisible(false);
    }



}
