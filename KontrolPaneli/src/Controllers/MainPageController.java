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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String comPortName = "";
		
		// kart aranıyor...
		if(SerialPort.getCommPorts() != null) {
			
			SerialPort[] portList = SerialPort.getCommPorts();
			
			for(SerialPort port: portList) {
				System.out.println(port.getSystemPortName());
				
				// Buradaki if ile arduinoya otomatik olarak bağlanabiliyoruz.
				if(port.getDescriptivePortName().contains("CH340"))
					comPortName = port.getSystemPortName();
			}
			
		}
		System.out.println(comPortName + "seçildi");
		// Karta bağlanma işlemi
		scm = SerialCommHandler.getInstance();
		boolean conn = scm.connectToPort(comPortName,115200);
		
		// komut halledici oluşturulup komutlar ekleniyor.
		comHandler = new CommandHandler();
		comHandler.registerCommand("S1 ", new SetNozzleTempCommand());
		comHandler.registerCommand("TEMP1 ", new GetNozzleTempCommand());
		
		// thread içeriinde çağırılacak comhandler ekleniyor.
		scm.setComHandler(comHandler);
		
		machine  = MachineInfo.getInstance();
		if(conn)
			testField.textProperty().bind(scm.commandProtperty());
		
		// bind etmek için floatpropery oluşturup lambda fonksiyonu ile bind işlemini gerçekleştiriyoruz.
		FloatProperty nozzleTemp = machine.getNozzleTempProperty();
		nozzleLabel.textProperty().bind(Bindings.createStringBinding(() ->
        	String.format("%.2f", nozzleTemp.get()), nozzleTemp));

		FloatProperty ortaTemp = machine.getMidTempProperty();
		ortaLabel.textProperty().bind(Bindings.createStringBinding(() ->
        	String.format("%.2f", ortaTemp.get()), ortaTemp));
		
		FloatProperty arkaTemp = machine.getBackTempProperty();
		arkaLabel.textProperty().bind(Bindings.createStringBinding(() ->
        	String.format("%.2f", arkaTemp.get()), arkaTemp));
	}

}
