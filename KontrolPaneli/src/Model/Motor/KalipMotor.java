package Model.Motor;

import Model.MachineConfig;

public class KalipMotor extends Motor {

	public KalipMotor() {
		super();
		this.kasnakOran = 10.24f;
		this.stepCountPerRev = 200;
		this.oneMMStepcount = (long)((this.kasnakOran * this.stepCountPerRev) / MachineConfig.milHatve); // cast kısmında hata çıkabilir buraya dikkat et
		
	}

}
