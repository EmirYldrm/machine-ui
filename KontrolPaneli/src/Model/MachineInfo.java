package Model;

public class MachineInfo {
	
	private static MachineInfo instance;
	
	private float nozzleSicaklik;
	private float midSicaklik;
	private float backSicaklik;
	private float targetNozzleSicaklik;
	private float targetMidSicaklik;
	private float targetBackSicaklik;
	
	private State currentState;
	
	private Motor enjeksiyonMotor;
	private Motor heleoznMotor;
	private Motor kalipMotor;
	
	private Process currentProcess;
	
	
	public float getNozzleSicaklik() {
		return nozzleSicaklik;
	}

	public void setNozzleSicaklik(float nozzleSicaklik) {
		this.nozzleSicaklik = nozzleSicaklik;
	}

	public float getMidSicaklik() {
		return midSicaklik;
	}

	public void setMidSicaklik(float midSicaklik) {
		this.midSicaklik = midSicaklik;
	}

	public float getBackSicaklik() {
		return backSicaklik;
	}

	public void setBackSicaklik(float backSicaklik) {
		this.backSicaklik = backSicaklik;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public Motor getEnjeksiyonMotor() {
		return enjeksiyonMotor;
	}

	public void setEnjeksiyonMotor(Motor enjeksiyonMotor) {
		this.enjeksiyonMotor = enjeksiyonMotor;
	}

	public Motor getHeleoznMotor() {
		return heleoznMotor;
	}

	public void setHeleoznMotor(Motor heleoznMotor) {
		this.heleoznMotor = heleoznMotor;
	}

	public Motor getKalipMotor() {
		return kalipMotor;
	}

	public void setKalipMotor(Motor kalipMotor) {
		this.kalipMotor = kalipMotor;
	}

	public Process getCurrentProcess() {
		return currentProcess;
	}

	public void setCurrentProcess(Process currentProcess) {
		this.currentProcess = currentProcess;
	}

	public float getTargetNozzleSicaklik() {
		return targetNozzleSicaklik;
	}

	public void setTargetNozzleSicaklik(float targetNozzleSicaklik) {
		this.targetNozzleSicaklik = targetNozzleSicaklik;
	}

	public float getTargetBackSicaklik() {
		return targetBackSicaklik;
	}

	public void setTargetBackSicaklik(float targetBackSicaklik) {
		this.targetBackSicaklik = targetBackSicaklik;
	}

	public float getTargetMidSicaklik() {
		return targetMidSicaklik;
	}

	public void setTargetMidSicaklik(float targetMidSicaklik) {
		this.targetMidSicaklik = targetMidSicaklik;
	}
	
	
	private MachineInfo() {
        // Private constructor to prevent instantiation from outside the class
    }
	
	public static synchronized MachineInfo getInstance() {
        if (instance == null) {
            instance = new MachineInfo();
        }
        return instance;
    }

	
	
}
