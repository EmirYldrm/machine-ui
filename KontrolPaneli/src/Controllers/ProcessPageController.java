
package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.MachineInfo;
import Model.ObjectSerializer;
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
    private Button retractionButton1;

    @FXML
    private TextField retractionField1;

    @FXML
    private Label retractionLabel;

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
    	float yeni = Float.parseFloat(str);
    	long adim = (long)(machine.getKalipMotor().oneMMStepcount * yeni);
    	SerialCommHandler.getInstance().sendString("PM " + adim);
       	
    	process.setKalipAdim((long)(machine.getKalipMotor().oneMMStepcount * yeni));
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
    	
    	float yeni = Float.parseFloat(str);
    	long adim = (long)(machine.getKalipMotor().oneMMStepcount * yeni);
    	
    	SerialCommHandler.getInstance().sendString("PP " + adim);
    	
    	process.setPinUzunluk(adim);
    	
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
    	System.out.println(machine.getEnjeksiyonMotor().oneMMStepcount);
    	SerialCommHandler.getInstance().sendString("PH " + (enjLen * machine.getEnjeksiyonMotor().oneMMStepcount));
    	
    	process.setParcaHacim(enjLen * machine.getEnjeksiyonMotor().oneMMStepcount);
    	
    	hacimLabel.setText(str);
    	
    	
    	
    }
    
    @FXML
    void setProcessRetraction(MouseEvent event) {
    	
    	String str = retractionField1.getText();
    	
    	float yeni = Float.parseFloat(str);
    	long adim = (long)(machine.getEnjeksiyonMotor().oneMMStepcount * yeni);
    	
    	SerialCommHandler.getInstance().sendString("PX " + adim);
    	
    	process.setRetruction(adim);
    	
    	retractionLabel.setText(str);
    }

    @FXML
    void updateTheProject(MouseEvent event) {
    	machine.setCurrentProcess(process);
    	ObjectSerializer serializer = new ObjectSerializer();
    	
    	try {
    		String userDir = System.getProperty("user.dir");
	        String fileName = "process.akl";
	        File file = new File(userDir, fileName);
	        Process process = new Process();
	        
	        if (file.exists()) {
	            System.out.println("File already exists in deserring" + userDir);
	            serializer.serializeObject(file.getPath() ,this.process);
	            
	        } else {
	            try {
	                if (file.createNewFile()) {
	                    System.out.println("File created successfully in " + userDir);
	
	                    // Serialize the object to the file
	                    serializer.serializeObject(file.getPath() ,this.process);
	
	                    // Do other stuff with the newly created file here
	                } else {
	                    System.out.println("Failed to create the file.");
	                }
	            } catch (IOException e) {
	                System.out.println("An error occurred while creating the file: " + e.getMessage());
	            }
	        }
	        
			System.out.println(process.getParcaDusurmeSayisi());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("serializer sikintisi");
		}
    }
    
    
    public void setCurrentProcess() {
    	
    	this.hacimLabel.setText(String.valueOf(process.getParcaHacim()));
    	this.coollabel.setText(String.valueOf(process.getBeklemeSuresi()));
    	this.dolHizLabel.setText(String.valueOf(process.getDolumHiz()));
    	this.enjHizLabel.setText(String.valueOf(process.getEnjeksiyonHiz()));
    	this.oranLabel.setText(String.valueOf(process.getEnjeksiyonHelezonFactor()));
    	this.fallLabel.setText(String.valueOf(process.getParcaDusurmeSayisi()));
    	this.sayiLabel.setText(String.valueOf(process.getHedefSayi()));
    	this.kalMaxLabel.setText(String.valueOf(process.getKalipAdim()));
    	this.isimLabel.setText(String.valueOf(process.getIsim()));
    	this.pinLenLabel.setText(String.valueOf(process.getPinUzunluk()));
    	this.retractionLabel.setText(String.valueOf(process.getRetruction()));
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Controller için lazım olan değişkenler 
		this.scm = SerialCommHandler.getInstance();
		this.comHandler = scm.getComHandler();
		this.machine  = MachineInfo.getInstance();
		
		if(machine.getCurrentProcess() == null) {
			process = new Process();
			System.out.println("çünkü null");
		}
		else {
			this.process = machine.getCurrentProcess();
			setCurrentProcess();
		}
		
	}

}
