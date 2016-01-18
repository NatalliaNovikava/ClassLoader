package epam.training.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import epam.training.model.Constants;

public class TXTFileReader extends MyFileReader{
	
	private List <String[]> data;
	
	
	public TXTFileReader() {
		super();
	}

	public void readFile(String fileName) throws IOException {
		data = new ArrayList <String[]>();
		String line = null;
		String [] row = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(Constants.PATH + fileName));
		    while ((line = bufferedReader.readLine()) != null)
			{
			    String []  str = line.split(" ");
                row = new String [str.length];
				for (int i = 0; i < str.length; i++) {
		            row[i] = str[i].trim();
				}
				data.add(row);
			}
		
		 } catch (FileNotFoundException e) {
             throw new IOException("File not found: " + fileName);
         } catch (IOException e) {
             throw new IOException("Unable to read file: " + fileName);
         } finally {
        	 try {
        		  if (bufferedReader != null) {
        		      bufferedReader.close();
        		  }
        	 } catch (IOException e) {
        		 throw new IOException("Unable to close file: " + fileName);
             }
         }
	}

	public List<String[]> getData() {
		return data;
	}
}
	
	

