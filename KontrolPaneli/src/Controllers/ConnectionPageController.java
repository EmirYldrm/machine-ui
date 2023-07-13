package Controllers;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXTextArea;

import Model.MachineInfo;
import Serial.SerialCommHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class ConnectionPageController implements Initializable{
///////////////////////////////////////////////////////////////

	private SerialPort sp;
	
	private MachineInfo machine;

	private OutputStream outputStream1;
	public  InputStream input;
	
	public SerialCommHandler scm;
	
	
	public String gelen;
	public String comPortName;
	
	
	public Scanner in;
	
///////////////////////////////////////////////////////////////

    @FXML
    private Button baglanButton;

    @FXML
    private TextField commandField;

    @FXML
    private JFXTextArea messageArea;

    @FXML
    private ComboBox<String> portBox;

    @FXML
    private Button sendButton;

    @FXML
    void connectToBoard(MouseEvent event) {
    	
    	scm = SerialCommHandler.getInstance();
		if(baglanButton.getText().equals("BAĞLAN")) {
			//boolean conn = scm.connectToPort(portBox.getValue(),115200);
			boolean conn = scm.connectToPort(this.comPortName,115200);
			if(conn) {
				messageArea.setVisible(true);
				messageArea.setText("...Bağlandı...");
				baglanButton.setText("KAPAT");
				sendButton.setVisible(true);
				commandField.setVisible(true);
				messageArea.textProperty().bind(scm.commandProtperty());
				
			}
		}else {
			scm.closePort();
		}
    }

    @FXML
    void sendCommand(MouseEvent event) {

    	
    	scm.sendString(commandField.getText());
    	commandField.clear();
  		commandField.setText(""); 
    }

    
    public void setSerialCommunicationHandler(SerialCommHandler serialHandler) {
    	this.scm = serialHandler;
    }
    
    public void setMachineInfo(MachineInfo machine) {
    	this.machine = machine;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		if(SerialPort.getCommPorts() != null) {
			SerialPort[] portList = SerialPort.getCommPorts();
			ObservableList<String> list = FXCollections.observableArrayList();
			for(SerialPort port: portList) {
				list.add(port.getSystemPortName());
				System.out.println(port.getSystemPortName());
				// Buradaki if ile arduinoya otomatik olarak bağlanabiliyoruz.
				if(port.getDescriptivePortName().contains("CH340"))
					this.comPortName = port.getSystemPortName();
			}
			portBox.setItems(list);
		}
		else
			System.out.println("port hata ");

		// Visibility settings
		messageArea.setEditable(false);
		commandField.setVisible(false);
		
	}

}
