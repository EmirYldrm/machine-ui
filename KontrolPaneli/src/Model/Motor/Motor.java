package Model.Motor;


public class Motor {

	public float kasnakOran;
	public long oneMMStepcount;
	public long stepCountPerRev;
	
	public long currentPosition;
	public long targetPosition;
	
	public boolean homeStatus = false;
	
	public Motor() {
		this.currentPosition = 0;
		this.targetPosition = 0;
	}
	
	public long grToStep(float hacim) {
	
		return 0;
	}
	
}
