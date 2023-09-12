package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ObjectDeserializer {
    public Serializable deserializeObject(String fileName) throws IOException, ClassNotFoundException {
    			FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Serializable ser = (Serializable) in.readObject();
                in.close();
                fileIn.close();
                return ser;
        
        
    }
}
