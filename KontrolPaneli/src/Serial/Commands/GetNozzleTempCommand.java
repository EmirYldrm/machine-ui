package Serial.Commands;

import Model.MachineInfo;

public class GetNozzleTempCommand implements ICommand{
	
	private float temperature;
	
	@Override
	public void execute() {
		MachineInfo.getInstance().setNozzleSicaklik(temperature);
		
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
