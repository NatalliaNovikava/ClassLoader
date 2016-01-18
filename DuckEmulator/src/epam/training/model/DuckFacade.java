package epam.training.model;

public class DuckFacade {

	private Duck duck;
	
	
	public DuckFacade(Duck duck) {
		this.duck = duck;
	}
    
	public void goUp() {
		int y = getCurrentY();
		getCurrentPosition().setY(y -= getDuckLengthStep());
	}
	public void goDown() {
		int y = getCurrentY();
		getCurrentPosition().setY(y += getDuckLengthStep());
	}
	
	public void goRight() {
		int x = getCurrentX();
		getCurrentPosition().setX(x += getDuckLengthStep());
	}

	public void goLeft() {
		int x = getCurrentX();
		getCurrentPosition().setX(x -= getDuckLengthStep());
	}

	private Position getCurrentPosition(){
		return duck.getPosition();
	}
	
	private int getDuckLengthStep(){
		return duck.getLengthStep();
	}
	
	private int getCurrentX() {
		return getXHelper(getCurrentPosition());
	}

	private int getXHelper(Position position ) {
		return position.getX();
	}

	private int getCurrentY() {
		return getYHelper(getCurrentPosition());
	}

	private int getYHelper(Position position ) {
		return position.getY();
	}
}
