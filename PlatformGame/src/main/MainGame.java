package main;

import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import Character.Crab_Spawn;
import Character.Player;
import KeyBoardInput.Sound;
import KeyBoardInput.SwitchAction;
import LoadMap.Load;
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
	
	public static int ChangeVolume = -23;
	public static boolean resetSong = false;
	public static boolean nextSong = false;
	private int songIndex = 2;
	
	public static boolean nextMap = false;
	
	private Sound sound;
	private Thread thread;
	private final int FPS = 120;
	private int takeAction;
	
	int i = 0;

	
	public static boolean pause = false;
	public static boolean restart = false;
	
	public MainGame() {
		
        sound = new Sound();
        getSound(songIndex);
        
		initializePlayer();
		
		panel_game = new Game_JPanel(this);
		window_game = new Game_JFrame_Window(panel_game);
		GameRepaintThread();
		
	}
	
	public void warning() {
		
	}
	
	public void initializePlayer() {
		Load.mapControler();
		map = new MapManager(this);
		player = new Player(30,300, (int) (96*SCALE), (int) (84*SCALE) );
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
       if(restart) {
    	   initializePlayer();
    	   restart = false;
       }
	   
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

			if(nextMap) { 
				if(Load.index == 0)
				   Load.index = 1;
				else
				   Load.index = 0;
				initializePlayer();
			}	
			nextMap = false;	
			
				
		
			
			if (System.currentTimeMillis() - lastCheck >= 5000) {
				if(!sound.checkActive()) getSound(songIndex);
				lastCheck = System.currentTimeMillis();
			}
			
			nowframe = System.currentTimeMillis();
			if(nowframe - loadframe >= 150) {
			    update();
				loadframe = System.currentTimeMillis();

			}
			
			if(resetSong)
			   sound.reset();
			resetSong = false;
			
            if(nextSong) {
            	if(songIndex == 2)
            		songIndex = 1;
            	else
            		songIndex = 2;
            	sound.nextSong(songIndex, ChangeVolume);
            }nextSong = false;
            
			if(ChangeVolume == -60) 
			   sound.fc.setValue(-75);
			else 
			   sound.fc.setValue(ChangeVolume);

		}
		
	}
	
	

	
	
	private void getSound(int i)  {
		try {
			sound.getSound(i ,-23);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
}
