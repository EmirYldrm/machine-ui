package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ObjectDeserializer {
    public Serializable deserializeObject(String fileName) throws IOException, ClassNotFoundException {
    	
    	
    	try (InputStream inputStream =new FileInputStream(fileName)) {
          
                ObjectInputStream in = new ObjectInputStream(inputStream);
                Serializable ser = (Serializable) in.readObject();
                in.close();
                return ser;
        
        }catch(IOException e){
        	throw e;
        }
        
        
    }
}
