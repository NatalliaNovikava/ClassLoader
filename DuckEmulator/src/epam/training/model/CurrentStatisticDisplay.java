package epam.training.model;

import epam.training.interfaces.DisplayElement;
import epam.training.interfaces.Observer;

public class CurrentStatisticDisplay implements Observer, DisplayElement{
	
	private int quantity_walls;
	private int quantity_passeges;
	private DataLabyrinth dataLabyrinth;
	
	
	public CurrentStatisticDisplay(DataLabyrinth dataLabyrinth) {
		this.dataLabyrinth = dataLabyrinth;
		dataLabyrinth.registerObserver(this);
	}

	@Override
	public void update(int quantity_walls, int quantity_passeges) {
		this.quantity_walls = quantity_walls;
		this.quantity_passeges = quantity_passeges;
		display();
	}


	@Override
	public void display() {
		StringBuilder builder = new StringBuilder();
		builder.append(Constants.STATISTIC_HEADER);
		builder.append(quantity_walls);
		builder.append(Constants.STATISTIC_WALLS);
		builder.append(quantity_passeges);
		builder.append(Constants.STATISTIC_PASSEGES);
		System.out.println(builder.toString());
	}
}
