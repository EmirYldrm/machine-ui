package Model;

public class EnjeksiyonMotor extends Motor{
	
	
	public EnjeksiyonMotor() {
		super();
		this.kasnakOran = 3.2f;
		this.stepCountPerRev = 200;
		this.oneMMStepcount = (long)((this.kasnakOran * this.stepCountPerRev) / MachineConfig.milHatve); // cast kısmında hata çıkabilir buraya dikkat et
		
	}

}
