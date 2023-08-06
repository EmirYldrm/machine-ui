package Serial.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler {
	
	private Map<String, ICommand> commandMap = new HashMap<>();

    public void registerCommand(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String commandString) {
    	
    	//System.out.println(commandString + "  comhandler executecomand");
    	// Eger komut parametreli 
    	if(commandString.contains(" ") == true) {
    		
	    	String[] parts = commandString.split("\\s+", 2); // Split command and parameters
	        String commandName = parts[0];
	        String parameter = parts[1];
	        
	        //System.out.println("isim = " + commandName + "  parametresi = " + parameter);
	
	        ICommand command = commandMap.get(commandName);
	        if (command != null) {
	            command.setParameter(parameter);
	            command.execute();
	        }
    	} else {	// Parametresiz ise buraya girecek
    		String commandName = commandString;
    		ICommand command = commandMap.get(commandName);
    		if(command != null) {
    			command.execute();
    		}
    	}
    }
    // Overload
    public void executeCommand(List<String> commandList) {
    	//System.out.println("herere ");    	
    	//System.out.println( commandList);
    	
    	for (String element : commandList) {
            // Pass the element to a function
            //System.out.println(element + " oldu len");
    		this.executeCommand(element);
        }
    	return;
    }
}
