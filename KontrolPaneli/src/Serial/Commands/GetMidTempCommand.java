package Serial.Commands;

import Model.MachineInfo;
import javafx.application.Platform;

public class GetMidTempCommand implements ICommand{
	
	
	private float temperature;	
	
	@Override
	public void execute() {
		Platform.runLater(() -> {
			MachineInfo.getInstance().setMidSicaklik(temperature);
	    });
		
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
