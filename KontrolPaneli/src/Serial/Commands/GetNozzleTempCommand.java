package Serial.Commands;

import Model.MachineInfo;
import javafx.application.Platform;

public class GetNozzleTempCommand implements ICommand{
	
	private float temperature;
	
	@Override
	public void execute() {
		//System.out.println("getnozzletemp = " + this.temperature);
		Platform.runLater(() -> {
			MachineInfo.getInstance().setNozzleSicaklik(temperature);
	    });
		
		
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
