package epam.training.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import epam.training.interfaces.Observer;
import epam.training.interfaces.Subject;

public class DataLabyrinth implements Subject{

	private Labyrinth labyrinth;
	private ArrayList<Observer> observers;
	
	
	public DataLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;	
		this.observers = new ArrayList<Observer>();
	}
	
	public void setCells(List<String[]> m_cells) throws IOException {
		this.labyrinth.setCells(m_cells);
		if (m_cells != null && !m_cells.isEmpty()) {
			dataChanged();
		}
	}
	
	private void dataChanged() {
		notifyObservers();
	}
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >=0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(labyrinth.getQuantity_walls(), labyrinth.getQuantity_passeges());
		}
	}
   
}
