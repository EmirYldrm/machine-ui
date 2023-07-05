package Model.Motor;

import Model.MachineConfig;

public class HelezonMotor extends Motor {
	
	public HelezonMotor() {
		super();
		this.kasnakOran = 4.8f;
		this.stepCountPerRev = 200;
		this.oneMMStepcount = (long)((this.kasnakOran * this.stepCountPerRev) / MachineConfig.milHatve); // cast kısmında hata çıkabilir buraya dikkat et
		
	}

}
