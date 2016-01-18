package epam.training.model;

import org.apache.log4j.Logger;
import epam.training.interfaces.Observer;

public class CurrentStaticLog implements Observer{

	private int quantity_walls;
	private int quantity_passeges;
	private DataLabyrinth dataLabyrinth;
//	private static final Logger log = Logger.getLogger(CurrentStaticLog.class);
	
	public CurrentStaticLog(DataLabyrinth dataLabyrinth) {
		this.dataLabyrinth = dataLabyrinth;
		dataLabyrinth.registerObserver(this);
	}

	@Override
	public void update(int quantity_walls, int quantity_passeges) {
		this.quantity_walls = quantity_walls;
		this.quantity_passeges = quantity_passeges;
		updateFile();
	}

    public void updateFile(){
		StringBuilder builder = new StringBuilder();
		builder.append(Constants.STATISTIC_HEADER);
		builder.append(quantity_walls);
		builder.append(Constants.STATISTIC_WALLS);
		builder.append(quantity_passeges);
		builder.append(Constants.STATISTIC_PASSEGES);
		
    //	log.info(builder.toString());
	}
}
