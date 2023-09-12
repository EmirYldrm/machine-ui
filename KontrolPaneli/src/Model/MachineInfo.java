package Model;

import Model.Motor.EnjeksiyonMotor;
import Model.Motor.HelezonMotor;
import Model.Motor.KalipMotor;
import Model.States.State;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class MachineInfo {
	
	private static MachineInfo instance;
	
	private FloatProperty nozzleSicaklik = new SimpleFloatProperty();
	private FloatProperty midSicaklik= new SimpleFloatProperty();
	private FloatProperty backSicaklik= new SimpleFloatProperty();
	private FloatProperty targetNozzleSicaklik= new SimpleFloatProperty();
	private FloatProperty targetMidSicaklik= new SimpleFloatProperty();
	private FloatProperty targetBackSicaklik= new SimpleFloatProperty();
	
	
	private State currentState;
	
	private EnjeksiyonMotor enjeksiyonMotor;
	private HelezonMotor heleoznMotor;
	private KalipMotor kalipMotor;
	
	private Process currentProcess;
	
	public boolean isPaused = false;
	
	
	public float getNozzleSicaklik() {
		return nozzleSicaklik.get();
	}

	public void setNozzleSicaklik(float nozzleSicaklik) {
		System.out.println("machine ınfo "+ this.nozzleSicaklik);
		this.nozzleSicaklik.set(nozzleSicaklik);
	}

	public float getMidSicaklik() {
		return midSicaklik.get();
	}

	public void setMidSicaklik(float midSicaklik) {
		this.midSicaklik.set(midSicaklik);;
	}

	public float getBackSicaklik() {
		return backSicaklik.get();
	}

	public void setBackSicaklik(float backSicaklik) {
		this.backSicaklik.set(backSicaklik);;
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
		return targetNozzleSicaklik.get();
	}

	public void setTargetNozzleSicaklik(float targetNozzleSicaklik) {
		this.targetNozzleSicaklik.set(targetNozzleSicaklik);;
	}

	public float getTargetBackSicaklik() {
		return targetBackSicaklik.get();
	}

	public void setTargetBackSicaklik(float targetBackSicaklik) {
		this.targetBackSicaklik.set(targetBackSicaklik);;
	}

	public float getTargetMidSicaklik() {
		return targetMidSicaklik.get();
	}

	public void setTargetMidSicaklik(float targetMidSicaklik) {
		this.targetMidSicaklik.set(targetMidSicaklik);;
	}
	// Property kısımlarını bind etmek için kullanılacak fonksiyonlar
	public FloatProperty getNozzleTempProperty() {
		
		return this.nozzleSicaklik;
	}
	
	public FloatProperty getMidTempProperty() {
		return this.midSicaklik;
	}
	
	public FloatProperty getBackTempProperty() {
		return this.backSicaklik;
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
