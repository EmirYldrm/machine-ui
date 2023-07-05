package Serial.Commands;

import Model.MachineInfo;

public class GetMidTempCommand implements ICommand{
	
	
	private float temperature;	
	
	@Override
	public void execute() {
		MachineInfo.getInstance().setMidSicaklik(temperature);
		
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
