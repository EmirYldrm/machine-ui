
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.MachineInfo;
import Model.Process;
import Serial.SerialCommHandler;
import Serial.Commands.CommandHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProcessPageController implements Initializable{

	////////////////////////////////
	private MachineInfo machine;
	private CommandHandler comHandler;
	private SerialCommHandler scm;
	private Process process;
	////////////////////////////////
    @FXML
    private Label coollabel;
    
    @FXML
    private Label dolHizLabel;

    @FXML
    private Label enjHizLabel;
    
    @FXML
    private Label fallLabel;
    
    @FXML
    private Label isimLabel;

    @FXML
    private Label kalMaxLabel;
    
    @FXML
    private Label oranLabel;
    
    @FXML
    private Label sayiLabel;
    
    @FXML
    private Label pinLenLabel;
    

    @FXML
    private Label hacimLabel;

    
	@FXML
    private Button coolTimeButton;

    @FXML
    private TextField coolTimeField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button fallCountButton;

    @FXML
    private TextField fallCountField;

    @FXML
    private Button fillSpeedButton;

    @FXML
    private TextField fillSpeedField;

    @FXML
    private Button injectionSpeedButton;

    @FXML
    private TextField injectionSpeedField;

    @FXML
    private Button kalipMaxButton;

    @FXML
    private TextField kalipMaxField;

    @FXML
    private Button oranButton;

    @FXML
    private TextField oranField;

    @FXML
    private TextField parcaHacimField;

    @FXML
    private Button parcaHacimSetButton;

    @FXML
    private Button pinLenghtButton;

    @FXML
    private TextField pinLenghtField;

    @FXML
    private Button processFieldButton;

    @FXML
    private TextField processNameField;

    @FXML
    private Button updateProcess;

    @FXML
    private Button urunSayisiButton;

    @FXML
    private TextField urunSayisiField;

    @FXML
    void setProcessCoolTime(MouseEvent event) {
    	String str = coolTimeField.getText();
    	
    	int sure = Integer.parseInt(str);
    	
    	SerialCommHandler.getInstance().sendString("PA " + sure);
    	
    	process.setBeklemeSuresi(sure);
    	
    	coollabel.setText(str);
    }

    @FXML
    void setProcessFallCount(MouseEvent event) {
    	String str = fallCountField.getText();
    	
    	int count = Integer.parseInt(str);
    	
    	SerialCommHandler.getInstance().sendString("PF " + count);
    	
    	process.setParcaDusurmeSayisi(count);
    	
    	fallLabel.setText(str);
    }

    @FXML
    void setProcessFillSpeed(MouseEvent event) {
    	String str = fillSpeedField.getText();
    	
    	float speed = Float.parseFloat(str);
    	
    	SerialCommHandler.getInstance().sendString("PD " + speed);
    	
    	process.setDolumHiz(speed); 
    	
    	dolHizLabel.setText(str);
	}

    @FXML
    void setProcessHedefSayi(MouseEvent event) {
    	String str = urunSayisiField.getText();
    	int sayi = Integer.parseInt(str);
    	
    	SerialCommHandler.getInstance().sendString("PU " + sayi);
    	
    	process.setHedefSayi(sayi);
    	
    	sayiLabel.setText(str);
    }

    @FXML
    void setProcessInjectionSpeed(MouseEvent event) {
    	String str = injectionSpeedField.getText();
    	
    	float speed = Float.parseFloat(str);
    	
    	SerialCommHandler.getInstance().sendString("PS " + speed);
    	
    	process.setEnjeksiyonHiz(speed); 
    	
    	enjHizLabel.setText(str);
    }

    @FXML
    void setProcessKalMax(MouseEvent event) {
    	String str = kalipMaxField.getText();
    	long max = Long.parseLong(str);
    	
    	SerialCommHandler.getInstance().sendString("PM " + machine.getKalipMotor().oneMMStepcount * max);
    	process.setKalipAdim(machine.getKalipMotor().oneMMStepcount * max);
    	kalMaxLabel.setText(str);
    }

    @FXML
    void setProcessName(MouseEvent event) {
    	String str = processNameField.getText();
    	
    	process.setIsim(str);
    	isimLabel.setText(str);
    }

    @FXML
    void setProcessPinLen(MouseEvent event) {
    	String str = pinLenghtField.getText();
    	
    	long max = Long.parseLong(str);
    	
    	SerialCommHandler.getInstance().sendString("PP " + machine.getKalipMotor().oneMMStepcount * max);
    	
    	process.setPinUzunluk(machine.getKalipMotor().oneMMStepcount * max);
    	
    	pinLenLabel.setText(str);
    }

    @FXML
    void setProcessRate(MouseEvent event) {
    	String str = oranField.getText();
    	float rate = Float.parseFloat(str);
    	
    	SerialCommHandler.getInstance().sendString("PO " + rate);
    	process.setEnjeksiyonHelezonFactor(rate);
    	
    	oranLabel.setText(str);
	}

    @FXML
    void setProcessVolume(MouseEvent event) {
    	
    	 
    	String str = parcaHacimField.getText();
    	
    	long max = Long.parseLong(str);
    	
    	long enjLen = max / ((long)(Math.PI * 156.25));
    	SerialCommHandler.getInstance().sendString("PH " + (enjLen * machine.getEnjeksiyonMotor().oneMMStepcount));
    	
    	process.setParcaHacim(enjLen * machine.getEnjeksiyonMotor().oneMMStepcount);
    	
    	hacimLabel.setText(str);
    	
    	
    	
    }

    @FXML
    void updateTheProject(MouseEvent event) {
    	machine.setCurrentProcess(process);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.scm = SerialCommHandler.getInstance();
		this.comHandler = scm.getComHandler();
		this.machine  = MachineInfo.getInstance();
		
		process = new Process();
	}

}
