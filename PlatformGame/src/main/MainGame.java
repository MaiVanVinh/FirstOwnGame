package main;

import java.awt.Graphics;

import Character.Player;
import KeyBoardInput.SwitchAction;
import LoadMap.MapManager;

public class MainGame implements Runnable{
	Game_JFrame_Window window_game;
	Game_JPanel panel_game;
	Player player ;
	MapManager map;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1f;
	public final static int TOTAL_TILES_IN_WIDTH = 26;
	public final static int TOTAL_TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TOTAL_TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TOTAL_TILES_IN_HEIGHT;
	
	private Thread thread;
	private final int FPS = 120;
	private int takeAction;
	public int isJumping;
	int i = 0;
	
	public MainGame() {
		initializePlayer();
		
		panel_game = new Game_JPanel(this);
		window_game = new Game_JFrame_Window(panel_game);
		
		GameRepaintThread();
		
	}
	
	public void initializePlayer() {
		map = new MapManager(this);
		player = new Player(100,100, (int) (64*SCALE), (int) (40*SCALE) );
		  
	}
	
	public void render(Graphics g) {
		map.get_Map_Level(g);
		player.renderPlayer(g);
	}
	
   public Player getPlayer() {   
	   return player;
   }

   public void update() {
	   takeAction = SwitchAction.action;
	   player.frame1 = (SwitchAction.GetFramesAction(takeAction) );
	   player.updatePlayer();
	   
   }
   
	
	public void GameRepaintThread() {
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		
		double nanoFPS = 1000000000.0  / FPS;
		long lastTime = System.nanoTime();
		long now = System.nanoTime();
		
		long loadframe = System.currentTimeMillis();
		long nowframe = System.currentTimeMillis();
		
		//int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(true) {
			
			
			now = System.nanoTime();
			
			if(now - lastTime >= nanoFPS) {
				panel_game.repaint();
				lastTime = now;
				//frames++;
			}
			
			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frames);
				//frames = 0;
			}
			
			nowframe = System.currentTimeMillis();

			if(nowframe - loadframe >= 150) {
			    update();
				loadframe = System.currentTimeMillis();

			}
			

			
			
		}
		
	}
    
	
}
