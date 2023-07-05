package Serial.Commands;

import Model.MachineInfo;
import Serial.SerialCommHandler;

public class SetMidTempCommand implements ICommand{


	   private float temperature;
	   private CommandHandler commandProcessor;
	   
		@Override
		public void execute() {
			SerialCommHandler.getInstance().sendString("S2 " + temperature);
			MachineInfo.getInstance().setTargetMidSicaklik(temperature);
		}
		
		@Override
		public void setParameter(String parameter) {
			
	        this.temperature = Float.parseFloat(parameter);
	        		
		}
}
