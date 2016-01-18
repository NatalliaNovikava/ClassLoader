package epam.training.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassloaderTest {

	public ClassloaderTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CustomClassLoader ccl = new CustomClassLoader(ClassloaderTest.class.getClassLoader());
	    try {
			 
		    //epam.training.Emulator source.txt
			  String progClass = args[0];
		      String progArgs[] = new String[args.length - 1];  
		      System.arraycopy(args, 1, progArgs, 0, progArgs.length);
			
		      Class<?> c = ccl.loadClass(progClass);
			  Class<?> mainArgType[] = { (new String[0]).getClass() };
			  Method main = c.getMethod("main", mainArgType);
			  Object argsArray[] = { progArgs };
	
	          try {
				main.invoke(null, argsArray);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
