package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataRepository {
	public <T> void save(T entity) throws IOException{
		String name = entity.getClass().getName();
		String fileName = name.substring(name.indexOf(".") + 1, name.length()).toLowerCase();
		File file = new File(fileName + ".bin");
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(entity);
		oos.close();
		
		
	};
	
	
	public <T> T load(Class<?> classType) throws ClassNotFoundException, IOException{
		
		String name = classType.getName();
		String fileName = name.substring(name.indexOf(".") + 1, name.length()).toLowerCase();
		File file = new File(fileName + ".bin");
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object obj = ois.readObject();
		ois.close();
		return (T) obj;
		};
	
	
}
