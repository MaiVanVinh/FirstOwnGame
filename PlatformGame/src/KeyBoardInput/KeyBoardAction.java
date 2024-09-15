package KeyBoardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game_JPanel;



public class KeyBoardAction implements KeyListener{
	
    private Game_JPanel jpanel;
	private boolean isAttacking;
    
    
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

	            case KeyEvent.VK_LEFT:

	                jpanel.getGame().getPlayer().state_ani = 1;
	                jpanel.getGame().getPlayer().setLeft(true);
	                SwitchAction.action = 1;
	            	
	                break;
	            case KeyEvent.VK_RIGHT:
	            	
	            	
	                jpanel.getGame().getPlayer().state_ani = 1;
	                jpanel.getGame().getPlayer().setRight(true);
	                SwitchAction.action = 1;
	            	
	                break;
	                
	            case 90:
	            	SwitchAction.action = 0;
	            	
	            	isAttacking = true;
	                jpanel.getGame().getPlayer().state_ani = 6;
	                jpanel.getGame().getPlayer().setAttack(true);
	                SwitchAction.attack = 2;
	            	break;
	            	
	            	
	    		case KeyEvent.VK_SPACE:
	    			jpanel.getGame().getPlayer().setJump(true);
	    			break;

	 
	        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 int keyCode = e.getKeyCode();
	        
	        switch (keyCode) {
	            case KeyEvent.VK_LEFT:
	            	
	            	if(!isAttacking) SwitchAction.attack = 0;
	            	jpanel.getGame().getPlayer().setLeft(false);
	            	SwitchAction.action = 0;
	            	
	                break;
	            case KeyEvent.VK_RIGHT:

	            	if(!isAttacking) SwitchAction.attack = 0;
	            	jpanel.getGame().getPlayer().setRight(false);
	            	SwitchAction.action = 0;
	            	
	                break;
	            case 90:
	            	
	            	isAttacking = false;
	            	jpanel.getGame().getPlayer().setAttack(false);
	            	
	                break;
	                
	    		case KeyEvent.VK_SPACE:
	    			jpanel.getGame().getPlayer().setJump(false);
	    			break;

	        }   
		
	}




}
