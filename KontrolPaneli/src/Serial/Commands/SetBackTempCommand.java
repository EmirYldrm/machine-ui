package Serial.Commands;

import Model.MachineInfo;
import Serial.SerialCommHandler;

public class SetBackTempCommand implements ICommand{


	   private float temperature;
	   private CommandHandler commandProcessor;
	   
		@Override
		public void execute() {
			SerialCommHandler.getInstance().sendString("S3 " + temperature);
			MachineInfo.getInstance().setTargetBackSicaklik(temperature);
		}
		
		@Override
		public void setParameter(String parameter) {
			
	        this.temperature = Float.parseFloat(parameter);
	        		
		}
}
