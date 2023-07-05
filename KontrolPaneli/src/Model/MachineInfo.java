package Model;

import Model.Motor.EnjeksiyonMotor;
import Model.Motor.HelezonMotor;

public class MachineInfo {
	
	private static MachineInfo instance;
	
	private float nozzleSicaklik;
	private float midSicaklik;
	private float backSicaklik;
	private float targetNozzleSicaklik;
	private float targetMidSicaklik;
	private float targetBackSicaklik;
	
	private State currentState;
	
	private EnjeksiyonMotor enjeksiyonMotor;
	private HelezonMotor heleoznMotor;
	private KalipMotor kalipMotor;
	
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

	public EnjeksiyonMotor getEnjeksiyonMotor() {
		return enjeksiyonMotor;
	}

	public void setEnjeksiyonMotor(EnjeksiyonMotor enjeksiyonMotor) {
		this.enjeksiyonMotor = enjeksiyonMotor;
	}

	public HelezonMotor getHeleoznMotor() {
		return heleoznMotor;
	}

	public void setHeleoznMotor(HelezonMotor heleoznMotor) {
		this.heleoznMotor = heleoznMotor;
	}

	public KalipMotor getKalipMotor() {
		return kalipMotor;
	}

	public void setKalipMotor(KalipMotor kalipMotor) {
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
	
	// Step motor objelerinin ayarlanması
	// Overload
	public static synchronized MachineInfo getInstance(EnjeksiyonMotor enjmotor, HelezonMotor helmotor, KalipMotor kalmotor) {
        if (instance == null) {
            instance = new MachineInfo();
            instance.setEnjeksiyonMotor(enjmotor);
            instance.setHeleoznMotor(helmotor);
            instance.setKalipMotor(kalmotor);
        }
        return instance;
    }
	
	
}
