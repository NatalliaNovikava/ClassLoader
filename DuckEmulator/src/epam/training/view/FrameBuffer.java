package epam.training.view;

import java.util.List;

import epam.training.model.Constants;
import epam.training.model.Duck;
import epam.training.model.Labyrinth;
import epam.training.model.Labyrinth.Block;

public class FrameBuffer {
	private Duck duck;
	private Labyrinth labyrinth;

	
	public FrameBuffer() {
		super();
	}

	public void display(){
		List <String[]> m_cells = labyrinth.getCells();
		int x = duck.getPosition().getX();
    	int y = duck.getPosition().getY();
    	
    	String oldValue = m_cells.get(y)[x];
    	m_cells.get(y)[x] = Duck.symbol;
		for(String[] cell: m_cells)
	    {
			for (int i = 0; i < cell.length; i++)
	        {
			  if (cell[i].equals(Block.WALL.toString())) {
	    	      System.out.print(Labyrinth.symbolWall);
			  }
			  else if (cell[i].equals(Block.PASSAGE.toString())) {
				  System.out.print(Labyrinth.symbolPassage);
			  }
			  else {
				  System.out.print(cell[i]);
			  }
	        } 
			System.out.println();
	    } 
		m_cells.get(y)[x] = oldValue;
    }
	
	public void clear() {
		for (int i=0; i<100; i++) { 
			System.out.println();
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
} 
  

