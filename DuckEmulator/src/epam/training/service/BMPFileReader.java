package epam.training.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import epam.training.model.Constants;
import epam.training.model.Labyrinth.Block;

public class BMPFileReader extends MyFileReader {
	
	private List <String[]> data;
	private final static String COLORREF_RED ="#FF0000";
	private final static String COLORREF_GREEN = "#00FF00";
	private final static String COLORREF_WHITE ="#FFFFFF";
	private final static String COLORREF_BLACK ="#000000";
	private static Map<Integer,String> map_colors = null;
	
	public BMPFileReader() {
		init();
	}

	public static void init() {
		map_colors = new HashMap<Integer,String>();
		map_colors.put(getRGBByRef(COLORREF_RED), Block.ENTRY.toString());
		map_colors.put(getRGBByRef(COLORREF_GREEN), Block.EXIT.toString());
		map_colors.put(getRGBByRef(COLORREF_WHITE), Block.PASSAGE.toString());
		map_colors.put(getRGBByRef(COLORREF_BLACK), Block.WALL.toString());
	}
	
	private static int getRGBByRef(String ref) {
		ref = ref.replace("#", "0x");
		return Color.decode(ref).getRGB();
	}
	
	
	public void readFile(String fileName) throws IOException {
		data = new ArrayList <String[]>();
	    String [] row = null;
	    BufferedImage image = null;
	    try{
	        image = ImageIO.read(new File(Constants.PATH + fileName));
		
		    for (int y = 0; y < image.getHeight(); y++) {
		    	 row = new String [image.getWidth()];
	     	  	 data.add(row);	 
	        }
		    
		    for (int x = 0; x < image.getWidth(); x++) {
		    	
		    	for (int y = 0; y < image.getHeight(); y++) {   
		    		 int rgb = image.getRGB(x, y);
		    		 String str = convertToData(rgb);
		    		 data.get(y)[x] = str;
		    	} 
		    }
	    } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + fileName);
        } catch (IOException e) {
            throw new IOException("Unable to read file: " + fileName);
        }
    }
	
	private String convertToData(int elementRGB) throws IOException {
		String value = null;	
		
		if (map_colors.containsKey(elementRGB)) {
			value = map_colors.get(elementRGB).toString();
		}
		else { 
			throw new IOException("Not correct data");
		}
		return 	value;
	}

	@Override
	public List<String[]> getData() {
		return data;
	}    
}