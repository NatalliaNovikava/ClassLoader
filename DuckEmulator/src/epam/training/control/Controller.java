package epam.training.control;

import java.io.IOException;
import java.util.List;

import epam.training.control.Listener.Command;
import epam.training.model.Constants;
import epam.training.model.CurrentStaticLog;
import epam.training.model.CurrentStatisticDisplay;
import epam.training.model.DataLabyrinth;
import epam.training.model.Duck;
import epam.training.model.DuckFacade;
import epam.training.model.FileAdapter;
import epam.training.model.Labyrinth;
import epam.training.model.Labyrinth.Block;
import epam.training.model.Position;
import epam.training.view.FrameBuffer;

public class Controller {
     private Labyrinth labyrinth;
     private Duck duck;
     private FrameBuffer buffer;
	
	 
	 public void init(List <String[]> data) throws IOException {
		labyrinth = new Labyrinth();
		//create decorator object
		DataLabyrinth dataLabyrinth = new DataLabyrinth(labyrinth);
		// create observer to display on console 
		CurrentStatisticDisplay currentDisplay = new CurrentStatisticDisplay(dataLabyrinth);
		// create observer to write in log file 
		CurrentStaticLog currentLog = new CurrentStaticLog(dataLabyrinth);
		// create adapter for write statistic in log file using DisplayElement interface
		FileAdapter fileAdapter = new FileAdapter(currentLog);
		//test console observer before init() labyrinth
		currentDisplay.display();
		//test file observer using adapter before init() labyrinth
		fileAdapter.display();
		dataLabyrinth.setCells(data);
		
		duck = new Duck(labyrinth.getEntryPosition());
		buffer = new FrameBuffer(); 
		buffer.setLabyrinth(labyrinth);
	    buffer.setDuck(duck);
		
	}
	
	public void doGame() {
		buffer.display();
		System.out.println(Constants.KEYS); 
		
		Listener listener = new Listener();
		do {
			Command command = listener.listen();
		    if (command != null && isAction(command))
		    {  
		    	buffer.clear();
		    	buffer.setDuck(duck);
			    buffer.display();  
		    }
		}
		while (isNotExit(duck.getPosition()));
	    System.out.println(Constants.CONGRATULATIONS);
	}
	
	private boolean isNotExit(Position position){
		List<Position> exitPositions = labyrinth.getExitPositions();
		return !exitPositions.contains(position);
		
	}
	
	
	private boolean isAction(Command command) {
		boolean isAction= false;
		Position position = new Position(duck.getPosition().getX(),duck.getPosition().getY());
		Duck testDuck = new Duck(position);
		DuckFacade testDuckFacade = new DuckFacade(testDuck);
		DuckFacade duckFacade = new DuckFacade(duck);
		
		switch (command) {
		
			case GO_UP: 
				testDuckFacade.goUp();             
			    if (isAvailableAction(testDuck.getPosition())) {
			    	duckFacade.goUp();
			 		isAction = true;
			 	}
			 	break;
			case GO_DOWN:
			    testDuckFacade.goDown();             
		        if (isAvailableAction(testDuck.getPosition())) {
		        	duckFacade.goDown(); 
					isAction = true;
				}
		
				break;
			case GO_RIGHT:
			    testDuckFacade.goRight();             
				if (isAvailableAction(testDuck.getPosition())) {
					duckFacade.goRight(); 
					isAction = true;
				}
				break;
			case GO_LEFT:
			    testDuckFacade.goLeft();             
				if (isAvailableAction(testDuck.getPosition())) {
					duckFacade.goLeft();
					isAction = true;
				}
				break;
		}
		if (!isAvailableAction(testDuck.getPosition())) {
			 System.out.println(Constants.MESSAGE_WALL);
		}
		return isAction;
	}
	
		
	 private boolean isAvailableAction(Position position){
			
		int newX = position.getX();
		int newY = position.getY();
		
		if (newX >= 0 && newY >= 0 && newX <= labyrinth.getMaxX() &&
				newY <= labyrinth.getMaxY() &&
			    !labyrinth.getCells().get(newY)[newX].equals(Block.WALL.toString()))
		{
			return true;
		}
		else
		{
		    return false;
	    }
	}

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}

	public Duck getDuck() {
		return duck;
	}

	public void setDuck(Duck duck) {
		this.duck = duck;
	}

	public FrameBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(FrameBuffer buffer) {
		this.buffer = buffer;
	}

}
