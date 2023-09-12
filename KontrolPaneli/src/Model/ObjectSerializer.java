package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectSerializer {
	public void serializeObject(String fileName, Serializable object) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(object);
			out.close();
			fileOut.close();	
        }
    }
}
