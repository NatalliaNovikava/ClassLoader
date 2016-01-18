package epam.training.service;

import java.io.IOException;
import java.util.List;

public abstract class MyFileReader {

	public MyFileReader() {
		super();
	}
	public abstract void readFile(String fileName) throws IOException;
	
	public abstract List<String[]> getData();
		
}
