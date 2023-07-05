package Serial.Commands;

import Model.MachineInfo;

public class GetBackTempCommand implements ICommand{

	private float temperature;	
	
	@Override
	public void execute() {
		MachineInfo.getInstance().setBackSicaklik(temperature);
		
	}
	
	@Override
	public void setParameter(String parameter) {
		
        this.temperature = Float.parseFloat(parameter);
        		
	}

}
