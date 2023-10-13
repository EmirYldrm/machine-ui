package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXButton;

import Model.MachineInfo;
import Serial.SerialCommHandler;
import Serial.Commands.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SideBarController implements Initializable{
///////////////////////////////////////////////////////
	MachineInfo machine;
	private CommandHandler comHandler;
	private SerialCommHandler scm;
/////////////////////////////////////////////////////
    @FXML
    private StackPane stackPane;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private JFXButton connectPageButton;

    @FXML
    private JFXButton mainPageButton;

    @FXML
    private JFXButton processPageButton;

    @FXML
    private JFXButton productionPageButton;

    
    @FXML
    void connectPage(MouseEvent event) {
    	loadPage("/view/ConnectionPage.fxml");
    }

    @FXML
    void mainPage(MouseEvent event) {
		loadPage("/view/MainPage.fxml");

    }

    @FXML
    void processPage(MouseEvent event) {
    	loadPage("/view/ProcessPage.fxml");
    }
    
    void loadPage(String page) {
    	try {
            Node pageNode = FXMLLoader.load(getClass().getResource(page));
            stackPane.getChildren().setAll(pageNode);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		String comPortName = "";
//		
//		// kart aranıyor...
//		if(SerialPort.getCommPorts() != null) {
//			
//			SerialPort[] portList = SerialPort.getCommPorts();
//			
//			for(SerialPort port: portList) {
//				System.out.println(port.getSystemPortName());
//				
//				// Buradaki if ile arduinoya otomatik olarak bağlanabiliyoruz.
//				if(port.getDescriptivePortName().contains("CH340"))
//					comPortName = port.getSystemPortName();
//			}
//			
//		}
//		System.out.println(comPortName + "seçildi");
//		
//		// komut halledici oluşturulup komutlar ekleniyor.
//		comHandler = new CommandHandler();
//		
//		comHandler.registerCommand("S1", new SetNozzleTempCommand());
//		comHandler.registerCommand("S2", new SetMidTempCommand());
//		comHandler.registerCommand("S3", new SetBackTempCommand());
//		
//		comHandler.registerCommand("TEMP1", new GetNozzleTempCommand());
//		comHandler.registerCommand("TEMP2", new GetMidTempCommand());
//		comHandler.registerCommand("TEMP3", new GetBackTempCommand());
//		
//		comHandler.registerCommand("B", new BeginProcessCommand());
//		comHandler.registerCommand("W", new WaitProcessCommand());
//		comHandler.registerCommand("C", new CancelProcessCommand());
//		
//		comHandler.registerCommand("PH", new SetProcessVolumeCommand());
//		comHandler.registerCommand("PU", new SetProcessPartCountCommand());
//		comHandler.registerCommand("PO", new SetProcessRateCommand());
//		comHandler.registerCommand("PS", new SetProcessInjectionSpeedCommand());
//		comHandler.registerCommand("PD", new SetProcessFillSpeedCommand());
//		comHandler.registerCommand("PF", new SetProcessFallCountCommand());
//		comHandler.registerCommand("PP", new SetProcessPinLengthCommand());
//		comHandler.registerCommand("PM", new SetProcessMoldMaxCommand());
//		comHandler.registerCommand("PA", new SetProcessCoolTimeCommand());
//
//        comHandler.registerCommand("KK", new MoveKalipMotorCommand());
//		comHandler.registerCommand("E0", new HomeEnjeksiyonMotorCommand());
//		comHandler.registerCommand("K0", new HomeKalipMotorCommand());
//		
//		
//		
//		
//		// Karta bağlanma işlemi
//		scm = SerialCommHandler.getInstance();
//		scm.setComHandler(comHandler);
//		boolean conn = scm.connectToPort(comPortName,115200);
//		
//		
//		
//		// thread içeriinde çağırılacak comhandler ekleniyor.
//		scm.setComHandler(comHandler);
		
		loadPage("/view/MainPage.fxml");
	}

}