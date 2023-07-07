package Serial.Commands;

import Model.MachineInfo;
import javafx.application.Platform;

public class GetBackTempCommand implements ICommand{

	private float temperature;	
	
	@Override
	public void execute() {
		Platform.runLater(() -> {
			MachineInfo.getInstance().setBackSicaklik(temperature);
	    });
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
