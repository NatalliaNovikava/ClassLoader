package epam.training.model;

public class Duck {

	public final static String symbol = "U";
    private int lengthStep = 1;
	private Position position;
	
	public Duck() {
		super();
	}
	
	public Duck(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getLengthStep() {
		return lengthStep;
	}

	public void setLengthStep(int lengthStep) {
		this.lengthStep = lengthStep;
	}
}
