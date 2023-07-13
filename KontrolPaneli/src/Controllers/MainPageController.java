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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainPageController implements Initializable{

	/////////////////////////////////////////////////////////////////////////////////////////////////
	MachineInfo machine;
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
    private Label currentProcessLabel;

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
    private Button kalipRightButton;

    @FXML
    private Label machineStatusLabel;

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
    
    public void setSerialCommunicationHandler(SerialCommHandler serialHandler) {
    	this.scm = serialHandler;
    }
    
    public void setMachineInfo(MachineInfo machine) {
    	this.machine = machine;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		machine  = MachineInfo.getInstance();
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
