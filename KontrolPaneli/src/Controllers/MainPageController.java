package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;

import Model.MachineInfo;
import Serial.SerialCommHandler;
import Serial.Commands.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainPageController implements Initializable{

	/////////////////////////////////////////////////////////////////////////////////////////////////
	private MachineInfo machine;
	private CommandHandler comHandler;
	private SerialCommHandler scm;
	/////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	private Label arkaLabel;
	
	@FXML
	private TextField arkaTempField;
	
	@FXML
	private Button arkaTempSetButton;
	
	@FXML
	private Label Label;
	
	@FXML
	private Button enjeksiyonHomeButton;
	
	@FXML
	private Label enjeksiyonKonumLabel;
	
	@FXML
	private Button kalipHomeButton;
	
	@FXML
	private Label kalipKonumLabel;
	
	@FXML
	private Button kalipLeftButton;
	
	@FXML
	private Label machineStatusLabel;
	
    @FXML
    private TextField moldDistanceField;
	
	@FXML
	private Button moveKalipMotor;
	
	@FXML
	private Label nozzleLabel;
	
	@FXML
	private TextField nozzleTempField;
	
	@FXML
	private Button nozzleTempSetButton;
	
	@FXML
	private Label ortaLabel;
	
	@FXML
	private TextField ortaTempField;
	
	@FXML
	private Button ortaTempSetButton;
	
	@FXML
	private ProgressBar processBar;
	
	@FXML
	private Button processBeginButton;
	
	@FXML
	private Button processSettingButton;
	
	@FXML
	private TextArea testField;

    @FXML
    void sendNozzleTemperature(MouseEvent event) {
		comHandler.executeCommand("S1 " + this.nozzleTempField.getText());
    }
    
    @FXML
    void enjeksiyonHome(MouseEvent event) {
    	this.scm.sendString("E0");
    }

    @FXML
    void homeKalipMotor(MouseEvent event) {
    	this.scm.sendString("K0");
    }

    @FXML
    void moveKalipLeft(MouseEvent event) {

    	String str = moldDistanceField.getText();
        try {
        	long distance = Long.parseLong(str);
        	long stepCount =  machine.getKalipMotor().oneMMStepcount * distance;
        	this.scm.sendString("KK " + stepCount);

        } catch (NumberFormatException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Geçersiz Giriş");
            alert.setContentText("Lütfen bir tam sayı girin.");
            alert.showAndWait();

            moldDistanceField.clear();
        }
    	
	}

    @FXML
    void moveKaliprRight(MouseEvent event) {
    	String str = moldDistanceField.getText();
        try {
        	long distance = Long.parseLong(str);
        	long stepCount =  machine.getKalipMotor().oneMMStepcount * distance;
        	this.scm.sendString("KK " + stepCount);

        } catch (NumberFormatException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Geçersiz Giriş");
            alert.setContentText("Lütfen bir tam sayı girin.");
            alert.showAndWait();

            moldDistanceField.clear();
        }
    	
    }
    
    @FXML
    void startProcess(MouseEvent event) {
    	this.scm.sendString("B");
    }
    
    public void setSerialCommunicationHandler(SerialCommHandler serialHandler) {
    	this.scm = serialHandler;
    }
    
    public void setMachineInfo(MachineInfo machine) {
    	this.machine = machine;
    }
    
    public void setComHandler(CommandHandler comHandler) {
    	this.comHandler = comHandler;
    }
    
    public void initalizepage() {
		
		if(scm != null)
			testField.textProperty().bind(scm.commandProtperty());
		
		
		// bind etmek için floatpropery oluşturup lambda fonksiyonu ile bind işlemini gerçekleştiriyoruz.
		FloatProperty nozzleTemp = machine.getNozzleTempProperty();
		FloatProperty midTemp = machine.getMidTempProperty();
		FloatProperty backTemp = machine.getBackTempProperty();
		
		nozzleLabel.textProperty().bind(nozzleTemp.asString());
		ortaLabel.textProperty().bind(midTemp.asString());
		arkaLabel.textProperty().bind(backTemp.asString());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.scm = SerialCommHandler.getInstance();
		this.comHandler = scm.getComHandler();
		this.machine  = MachineInfo.getInstance();
		
		if(scm != null)
			testField.textProperty().bind(scm.commandProtperty());
		
		
		// bind etmek için floatpropery oluşturup lambda fonksiyonu ile bind işlemini gerçekleştiriyoruz.
		FloatProperty nozzleTemp = machine.getNozzleTempProperty();
		FloatProperty midTemp = machine.getMidTempProperty();
		FloatProperty backTemp = machine.getBackTempProperty();
		
		nozzleLabel.textProperty().bind(nozzleTemp.asString());
		ortaLabel.textProperty().bind(midTemp.asString());
		arkaLabel.textProperty().bind(backTemp.asString());
		
	}

}
