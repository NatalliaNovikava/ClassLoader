package epam.training.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import epam.training.model.Constants;


	public class Listener{ 
	
	public enum Command{
		GO_UP, GO_DOWN, GO_RIGHT, GO_LEFT
	}	
		
		
	public Listener() {
		super();
	}

	public Command listen() { 
	     
		Command command = null;
		char c; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    try {
			c = (char) br.read();
		
			switch (c) {
			
				case('w'):
					command = Command.GO_UP;
					break;
				case('s'):
					command = Command.GO_DOWN;
					break;	
				case('d'):
					command = Command.GO_RIGHT;
					break;
				case('a'):
					command = Command.GO_LEFT;
				    break;
				default: System.out.println(Constants.KEYS);
				    break;
			}
		
		} catch (IOException e) {
			System.out.println("Reading error");
		} 
		return command;
	}
}
