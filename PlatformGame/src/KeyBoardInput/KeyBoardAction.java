package KeyBoardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game_JPanel;



public class KeyBoardAction implements KeyListener{
	
    private Game_JPanel jpanel;
	
    
    
	public KeyBoardAction(Game_JPanel panel) {
          this.jpanel = panel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
       
        }
		
	

	@Override
	public void keyPressed(KeyEvent e) {
		 int keyCode = e.getKeyCode();
	        
	        switch (keyCode) {
	            case KeyEvent.VK_UP:
	   
	            	SwitchAction.action = 1;
	                jpanel.getGame().getPlayer().state_ani = 1;
	                jpanel.getGame().getPlayer().dirNumber = 0;
	                
	                
	                break;
	            case KeyEvent.VK_DOWN:
	            	
	            	SwitchAction.action = 1;
	                jpanel.getGame().getPlayer().state_ani = 1;
	                jpanel.getGame().getPlayer().dirNumber = 1;
	                
	            	
	                break;
	            case KeyEvent.VK_LEFT:
	            	
	            	SwitchAction.action = 1;;
	                jpanel.getGame().getPlayer().state_ani = 1;
	                jpanel.getGame().getPlayer().dirNumber = 2;
	                
	            	
	                break;
	            case KeyEvent.VK_RIGHT:
	            	
	            	SwitchAction.action = 1;
	                jpanel.getGame().getPlayer().state_ani = 1;
	                jpanel.getGame().getPlayer().dirNumber = 3;
	            	
	            	
	                break;
	                
	            case 90:
	            	SwitchAction.action = 2;
	                jpanel.getGame().getPlayer().state_ani = 6;
	                jpanel.getGame().getPlayer().dirNumber = 4;
	                
	            	break;
	 
	        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 int keyCode = e.getKeyCode();
	        
	        switch (keyCode) {
	            case KeyEvent.VK_UP:
	            	SwitchAction.action = 0;
	            	jpanel.getGame().getPlayer().setUp(false);

	                break;
	            case KeyEvent.VK_DOWN:
	            	SwitchAction.action = 0;
	            	jpanel.getGame().getPlayer().setDown(false);
	            	
	                break;
	            case KeyEvent.VK_LEFT:
	            	SwitchAction.action = 0;
	            	jpanel.getGame().getPlayer().setLeft(false);
	            	
	                break;
	            case KeyEvent.VK_RIGHT:
	            	SwitchAction.action = 0;
	            	jpanel.getGame().getPlayer().setRight(false);
	                break;
	            case 90:
	            
	            	SwitchAction.action = 0;
	            	jpanel.getGame().getPlayer().setAttack(false);
	                break;
	        }   
		
	}




}
