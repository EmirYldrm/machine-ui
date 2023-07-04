package Model;

import Model.MachineConfig;
public class Motor {

	public float kasnakOran;
	public long oneMMStepcount;
	public long stepCountPerRev;
	
	public long currentPosition;
	public long targetPosition;
	
	public boolean homeStatus = false;
	
	public Motor() {}
	
	public long grToStep(float hacim) {
	
		return 0;
	}
	
}
