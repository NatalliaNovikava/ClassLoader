package epam.training;

import java.io.IOException;
import java.util.List;
import epam.training.control.Controller;
import epam.training.service.MyFileReader;
import epam.training.service.ReaderFactory;

public class Emulator {

	public static String getFileExtention(String fullFileName){
		int dotPos = fullFileName.lastIndexOf(".") + 1;
		return fullFileName.substring(dotPos);
	}

	public static void main(String[] args) {
		
       if(args.length < 1) {
 	       System.out.println("Error, usage: java ClassName inputfile");
		   System.exit(1);
		}
       
       MyFileReader reader = null;
       
       try{
    	  String fileExt = getFileExtention(args[0]); 
    	  reader = ReaderFactory.getClassFromFactory(fileExt);
       } catch (IllegalArgumentException e){
    	   System.out.println(e);  
       }
       
       try {
    	    if (reader != null) {
	    	    reader.readFile(args[0]);
	            List <String[]> data = reader.getData();
				if (data != null && !data.isEmpty()) {
					Controller controller = new Controller();
					controller.init(data);
					controller.doGame();
				}
				else {
					System.out.println("File is empty");
				}
    	    }	
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
