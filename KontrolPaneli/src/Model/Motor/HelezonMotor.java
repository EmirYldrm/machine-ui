package Model.Motor;

import Model.MachineConfig;

public class HelezonMotor extends Motor {
	
	public HelezonMotor() {
		super();
		this.kasnakOran = MachineConfig.helezonKasnakOran;
		this.stepCountPerRev = MachineConfig.stepPerRev;
		this.oneMMStepcount = (long)((this.kasnakOran * this.stepCountPerRev) / MachineConfig.milHatve); // cast kısmında hata çıkabilir buraya dikkat et
		
	}

}
