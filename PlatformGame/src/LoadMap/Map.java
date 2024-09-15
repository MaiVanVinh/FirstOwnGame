package LoadMap;

public class Map {
	private int[][] levelData;
    
    Map(int[][] levelData2){
    	
    	this.levelData = levelData2;
    }
    
    
    public int get_levelData(int x, int y) {
	    return levelData[y][x];
   }

}
