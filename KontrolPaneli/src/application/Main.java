package application;
	
import com.fazecast.jSerialComm.SerialPort;

import Controllers.ConnectionPageController;
import Controllers.MainPageController;
import Controllers.SideBarController;
import Serial.SerialCommHandler;
import Serial.Commands.BeginProcessCommand;
import Serial.Commands.CancelProcessCommand;
import Serial.Commands.CommandHandler;
import Serial.Commands.GetBackTempCommand;
import Serial.Commands.GetMidTempCommand;
import Serial.Commands.GetNozzleTempCommand;
import Serial.Commands.HomeEnjeksiyonMotorCommand;
import Serial.Commands.HomeKalipMotorCommand;
import Serial.Commands.MoveKalipMotorCommand;
import Serial.Commands.SetBackTempCommand;
import Serial.Commands.SetMidTempCommand;
import Serial.Commands.SetNozzleTempCommand;
import Serial.Commands.SetProcessCoolTimeCommand;
import Serial.Commands.SetProcessFallCountCommand;
import Serial.Commands.SetProcessFillSpeedCommand;
import Serial.Commands.SetProcessInjectionSpeedCommand;
import Serial.Commands.SetProcessMoldMaxCommand;
import Serial.Commands.SetProcessPartCountCommand;
import Serial.Commands.SetProcessPinLengthCommand;
import Serial.Commands.SetProcessRateCommand;
import Serial.Commands.SetProcessVolumeCommand;
import Serial.Commands.WaitProcessCommand;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/view/SideBar.fxml"));
//            Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Enjeksiyon Makinesi Kontrol Paneli");
//			//primaryStage.initStyle(StageStyle.TRANSPARENT); // Title bar'ı transparant yapiyor.
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		 try {
			   // Seri haberleşme başlatılıyor.
			 	SerialCommHandler serialCommunicationHandler  = initSerialComm();
			 	
			 	// Side Bar seri haberleşmesi instance ekleniyor.
		        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/view/SideBar.fxml"));
		        Parent sidebarRoot = sidebarLoader.load();
		        SideBarController sidebarController = sidebarLoader.getController();
		        sidebarController.setSerialCommunicationHandler(serialCommunicationHandler);
		        
		        // Main page  seri haberleşme instance ekleniyor
		        FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("/view/MainPage.fxml"));
		        Parent page1Root = mainPageLoader.load();
		        MainPageController mainController = mainPageLoader.getController();
		        mainController.setSerialCommunicationHandler(serialCommunicationHandler);
		        
		        // Connection page seri haberleşme instance ekleniyor
		        FXMLLoader connectionLoader = new FXMLLoader(getClass().getResource("/view/ConnectionPage.fxml"));
		        Parent page2Root = connectionLoader.load();
		        ConnectionPageController page1Controller = connectionLoader.getController();
		        page1Controller.setSerialCommunicationHandler(serialCommunicationHandler);
		        


		        // Instantiate the SerialCommunicationHandler
		        //SerialCommunicationHandler serialCommunicationHandler = new SerialCommunicationHandler();
		       

		        Scene scene = new Scene(sidebarRoot);
		        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		        primaryStage.setScene(scene);
		        primaryStage.setTitle("Enjeksiyon Makinesi Kontrol Paneli");
		        primaryStage.show();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public SerialCommHandler initSerialComm() {
		String comPortName = "";
		CommandHandler comHandler;
		SerialCommHandler scm;
		
		// kart aranıyor...
		if(SerialPort.getCommPorts() != null) {
			
			SerialPort[] portList = SerialPort.getCommPorts();
			
			for(SerialPort port: portList) {
				System.out.println(port.getSystemPortName());
				
				// Buradaki if ile arduinoya otomatik olarak bağlanabiliyoruz.
				if(port.getDescriptivePortName().contains("CH340") || port.getDescriptivePortName().contains("Arduino"))
					comPortName = port.getSystemPortName();
			}
			
		}
		System.out.println(comPortName + "seçildi");
		
		// komut halledici oluşturulup komutlar ekleniyor.
		comHandler = new CommandHandler();
		
		comHandler.registerCommand("S1", new SetNozzleTempCommand());
		comHandler.registerCommand("S2", new SetMidTempCommand());
		comHandler.registerCommand("S3", new SetBackTempCommand());
		
		comHandler.registerCommand("TEMP1", new GetNozzleTempCommand());
		comHandler.registerCommand("TEMP2", new GetMidTempCommand());
		comHandler.registerCommand("TEMP3", new GetBackTempCommand());
		
		comHandler.registerCommand("B", new BeginProcessCommand());
		comHandler.registerCommand("W", new WaitProcessCommand());
		comHandler.registerCommand("C", new CancelProcessCommand());
		
		comHandler.registerCommand("PH", new SetProcessVolumeCommand());
		comHandler.registerCommand("PU", new SetProcessPartCountCommand());
		comHandler.registerCommand("PO", new SetProcessRateCommand());
		comHandler.registerCommand("PS", new SetProcessInjectionSpeedCommand());
		comHandler.registerCommand("PD", new SetProcessFillSpeedCommand());
		comHandler.registerCommand("PF", new SetProcessFallCountCommand());
		comHandler.registerCommand("PP", new SetProcessPinLengthCommand());
		comHandler.registerCommand("PM", new SetProcessMoldMaxCommand());
		comHandler.registerCommand("PA", new SetProcessCoolTimeCommand());

        comHandler.registerCommand("KK", new MoveKalipMotorCommand());
		comHandler.registerCommand("E0", new HomeEnjeksiyonMotorCommand());
		comHandler.registerCommand("K0", new HomeKalipMotorCommand());
		
		
		
		
		// Karta bağlanma işlemi
		scm = SerialCommHandler.getInstance();
		scm.setComHandler(comHandler);
		boolean conn = scm.connectToPort(comPortName,115200);
		
		
		
		// thread içeriinde çağırılacak comhandler ekleniyor.
		scm.setComHandler(comHandler);
		if(scm == null) {
			System.out.println("walla null ula ");
		}
		return scm;
	}
}
