package epam.training.service;

import epam.training.service.BMPFileReader;
import epam.training.service.TXTFileReader;

public class ReaderFactory {

	public ReaderFactory() {
		// TODO Auto-generated constructor stub
	}
	private static enum FileKind {
		TXT, BMP
	}
	
	public static MyFileReader getClassFromFactory(String fileExt) throws IllegalArgumentException {
		try {
			FileKind kind = FileKind.valueOf(fileExt.toUpperCase());
			switch(kind) {
				case TXT : 
					return new TXTFileReader();
				case BMP:
					return new BMPFileReader(); 
				default : 
					throw new IllegalArgumentException("Unable to define type of file");
			}
		} catch (IllegalArgumentException e){
			throw new IllegalArgumentException("Unable to define type of file");
		}
	}
}
