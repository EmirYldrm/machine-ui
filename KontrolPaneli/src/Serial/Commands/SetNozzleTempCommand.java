package Serial.Commands;

import Model.MachineInfo;
import Serial.SerialCommHandler;
import javafx.application.Platform;

public class SetNozzleTempCommand implements ICommand{

   private float temperature;
   private CommandHandler commandProcessor;
   
	@Override
	public void execute() {
		SerialCommHandler.getInstance().sendString("S1 " + temperature);
		
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
