package KeyBoardInput;

public class SwitchAction {
    private static final int STANDING = 0;
    private static final int RUNING = 1;
    private static final int ATTACKING = 2;
    public static int action;
    
    public static int GetFramesAction(int action) {
    	switch(action) {
    	case STANDING:
    		return 5;
    	case RUNING:
    		return 6;
    	case ATTACKING:
    		return 3;
    	}
    	return 0;
    }
    
    
}
