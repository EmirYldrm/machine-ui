package application;
	
import java.io.File;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

import Controllers.ConnectionPageController;
import Controllers.MainPageController;
import Controllers.SideBarController;
import Model.MachineInfo;
import Model.Motor.EnjeksiyonMotor;
import Model.Motor.HelezonMotor;
import Model.Motor.KalipMotor;
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
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		
		 try {
			 	FXMLLoader videoPageLoader = new FXMLLoader(getClass().getResource("/view/VideoPage.fxml"));
		        Parent videoPageRoot = videoPageLoader.load();
		        Scene scene12 = new Scene(videoPageRoot);
		        primaryStage.initStyle(StageStyle.UNDECORATED);
		        primaryStage.setScene(scene12);
		        primaryStage.show();
		        
			/*// Create a Media object with the path to your video file
			 	File file = new File("../KontrolPaneli/src/Assets/icons/vids/akil_intro.mp4");	
				Media media = new Media(file.toURI().toString());

		        // Create a MediaPlayer with the Media object
		        MediaPlayer mediaPlayer = new MediaPlayer(media);

		        // Create a MediaView and bind it to the MediaPlayer
		        MediaView mediaView = new MediaView(mediaPlayer);

		        // Set the size of the MediaView to fit your application window
		        StackPane stackPane = new StackPane();
		        stackPane.getChildren().add(mediaView);
		        stackPane.setStyle("-fx-background-color: black;");
		        
		        mediaView.setFitWidth(800);
		        mediaView.setFitHeight(480);

		        // Create the scene and add the MediaView to it
		        Scene scene1 = new Scene(stackPane);

		        // Set the scene to the primary stage
		        primaryStage.setScene(scene1);
		    

		        mediaPlayer.play();
		        
		        mediaPlayer.setOnEndOfMedia(() -> {
		            
		            

		            // Now, you can continue with the rest of your application
		            // Add your code for initializing and displaying the main part of the application here

					 
			        
					   // Seri haberleşme başlatılıyor.
					 	SerialCommHandler serialCommunicationHandler  = initSerialComm();
					 	
					 	MachineInfo machine = MachineInfo.getInstance();
					 	machine.setEnjeksiyonMotor(new EnjeksiyonMotor());
					 	machine.setKalipMotor(new KalipMotor());
					 	machine.setHeleoznMotor(new HelezonMotor());
					 	
					 	// Side Bar seri haberleşmesi instance ekleniyor.
				        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/view/SideBar.fxml"));
				        Parent sidebarRoot = null;
						try {
							sidebarRoot = sidebarLoader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        SideBarController sidebarController = sidebarLoader.getController();
				        sidebarController.setSerialCommunicationHandler(serialCommunicationHandler);
				        sidebarController.setMachineInfo(machine);
				       


				        Scene scene = new Scene(sidebarRoot);
				        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				        primaryStage.setScene(scene);
				        primaryStage.setTitle("Enjeksiyon Makinesi Kontrol Paneli");
				        primaryStage.show();
				        
		        });

		        primaryStage.show();
*/
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
		boolean conn = scm.connectToPort(comPortName, 115200);
		
		if(conn == false) {
			System.out.println("conn olmadi");
		}
		
		// thread içeriinde çağırılacak comhandler ekleniyor.
		scm.setComHandler(comHandler);

		return scm;
	}
}
