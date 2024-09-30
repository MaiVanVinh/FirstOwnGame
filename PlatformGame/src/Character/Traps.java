package Character;



public class Traps extends Entity {

	public Traps(float x, float y) {
		super(x, y, 48, 25);
		
	}

	public void update(int offSetX) {
		int dx = (int) ( (x-offSetX) - Player.PlayerX_RightPos);
		int dy = (int) (Player.PlayerY_UpPos - (y + 25));
		if(Math.abs(dy) > 100)
			return;
		if(dx < 15 && dx > -52)
			Player.PlayerGetHitRight = true;
	}



	

}
