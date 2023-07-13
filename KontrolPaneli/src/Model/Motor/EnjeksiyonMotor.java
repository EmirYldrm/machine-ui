package Model.Motor;

import Model.MachineConfig;

public class EnjeksiyonMotor extends Motor{
	
	
	public EnjeksiyonMotor() {
		super();
		this.kasnakOran = MachineConfig.enjeksiyonKasnakOrani;
		this.stepCountPerRev = MachineConfig.stepPerRev;
		this.oneMMStepcount = (long)((this.kasnakOran * this.stepCountPerRev) / MachineConfig.milHatve); // cast kısmında hata çıkabilir buraya dikkat et
		
	}

}
