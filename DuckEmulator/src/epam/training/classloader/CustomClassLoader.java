package epam.training.classloader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;


public class CustomClassLoader extends ClassLoader{
	private ClassLoader parent;
	private static final Logger log = Logger.getLogger(CustomClassLoader.class);
	// Creates a new class loader using the specified parent class loader for delegation.
	public CustomClassLoader(ClassLoader parent) {
		super(parent);
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException{
		
		synchronized (getClassLoadingLock(name)) {
			// first check if class has already been loaded
			Class<?> c = findLoadedClass(name);
		
			if (c == null) {
			
				try {
						if (parent != null) {
						c = parent.loadClass(name);
					} else {
						ClassLoader bootstrapLoader = ClassLoader.getSystemClassLoader().getParent();
						c = bootstrapLoader.loadClass(name);
					}
				} catch (ClassNotFoundException e) {
					throw new ClassNotFoundException("%Class "+ name +" was not loaded");
				} 
			    if (c == null) {
			    
			    	c = findClass(name);
			    	resolveClass(c);
			    }
			}
			log.info("%Class "+ name +" was loaded");
			System.out.println("%Class "+ name +" was loaded");
			return c;
		}
		
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException{
		Class<?> result = findLoadedClass(name);
		if (result !=null) {
			log.info("%Class "+ name +"found in cache");
			return result;
		}
		String fileName = name.replace('.','/')+".class";
		
	    try {
	    	byte[] classBytes = loadClassFileData(fileName);
	    	result = defineClass(name, classBytes, 0, classBytes.length);
	    	
	    } catch(IOException e) {
	    	throw new ClassNotFoundException("Cannot load class" + name + ":" + e);
	    } catch (ClassFormatError e) {
	    	throw new ClassNotFoundException("Format of class file incorrect for class" + name + ":" + e);
	    }
	    return result;
	
	}

	private byte[] loadClassFileData(String name) throws IOException {
		DataInputStream in = null;
		try {
			InputStream stream = getClass().getClassLoader().getResourceAsStream(
	                name);
	        int size = stream.available();
	        byte buff[] = new byte[size];
	        in = new DataInputStream(stream);
	        in.readFully(buff);
	        return buff;
		} catch (IOException e) {
			throw new IOException("Cannot read the file");
		} finally { 
			if (in != null) {
				in.close();
			}
		}
	}
}
