package KeyBoardInput;

public class SwitchAction {
    private static final int STANDING = 0;
    private static final int RUNING = 1;
    public static int action;
    public static int attack;
    
    public static int GetFramesAction(int action) {
    	
    	if(attack > 0) return 3;
    		
    	      switch(action) {
    	         case STANDING:
    		       return 4;
    	         case RUNING:
    		       return 5;
    	        }

    	
    	return 3;
    }
    
    
}
