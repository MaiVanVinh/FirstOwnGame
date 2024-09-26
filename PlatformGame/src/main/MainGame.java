package main;

import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import Character.Crab_Spawn;
import Character.Player;
import KeyBoardInput.Sound;
import KeyBoardInput.SwitchAction;
import LoadMap.MapManager;

public class MainGame implements Runnable{
	
	Game_JFrame_Window window_game;
	Game_JPanel panel_game;
    PauseMenu pauseMenu;
	Player player ;
	Crab_Spawn crab;
	MapManager map;
	
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TOTAL_TILES_IN_WIDTH = 26;
	public final static int TOTAL_TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TOTAL_TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TOTAL_TILES_IN_HEIGHT;
	
	private Sound sound;
	private Thread thread;
	private final int FPS = 120;
	private int takeAction;
	public int isJumping;
	int i = 0;
	int soundIndex = 2;
	
	public static boolean pause = false;
	
	public MainGame() {
		
        sound = new Sound();
        getSound(soundIndex);
        
		initializePlayer();
		
		panel_game = new Game_JPanel(this);
		window_game = new Game_JFrame_Window(panel_game);
		GameRepaintThread();
		
	}
	
	public void warning() {
		
	}
	
	public void initializePlayer() {
//		pauseMenu = new PauseMenu();
//		pauseMenu.setVisible(true);
		map = new MapManager(this);
		player = new Player(20,100, (int) (64*SCALE), (int) (40*SCALE) );
		crab = new Crab_Spawn();
		  
	}
	
	public void render(Graphics g) {
		
		map.player_xPos = player.xPos;
		map.get_Map_Level(g);
	
	    if(!pause) {
		   player.updateBigMap = map.xLvlOffset;
		   player.renderPlayer(g);
		
		   crab.Offset =  map.xLvlOffset;
		   crab.renderCrabs(g);
	    }	

	}
	
   public Player getPlayer() {   
	   return player;
   }

   public void update() {
	   takeAction = SwitchAction.action;
	   player.frame1 = (SwitchAction.GetFramesAction(takeAction));
	   crab.updateCrabState();
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
		
		
		
		long lastCheck = System.currentTimeMillis();
		
		while(true) {
			
			
			now = System.nanoTime();
			
			if(now - lastTime >= nanoFPS) {
				panel_game.repaint();
				lastTime = now;
			}

		
			
			if (System.currentTimeMillis() - lastCheck >= 5000) {
//				if(!pause)
//				   pause = true;
//				else
//				   pause = false;
//				if(!sound.checkActive()) getSound(soundIndex);
				lastCheck = System.currentTimeMillis();
			}
			
			nowframe = System.currentTimeMillis();
			if(nowframe - loadframe >= 170) {
			    update();
				loadframe = System.currentTimeMillis();

			}
			

			
			
		}
		
	}
	
	

	
	
	private void getSound(int i)  {
		try {
			sound.getSound(i ,-10);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
}
