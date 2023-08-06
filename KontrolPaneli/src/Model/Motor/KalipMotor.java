package Model.Motor;

import Model.MachineConfig;

public class KalipMotor extends Motor {

	public KalipMotor() {
		super();
		this.kasnakOran = MachineConfig.kalipKasnakOran;
		this.stepCountPerRev = MachineConfig.stepPerRev;
		this.oneMMStepcount = ((this.kasnakOran * this.stepCountPerRev) / MachineConfig.milHatve); // cast kısmında hata çıkabilir buraya dikkat et
		this.oneMMStepcount = this.oneMMStepcount / 1.02f;
	}

}
