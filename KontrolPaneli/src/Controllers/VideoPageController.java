package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;

import Model.MachineInfo;
import Model.Process;
import Model.ObjectDeserializer;
import Model.Motor.EnjeksiyonMotor;
import Model.Motor.HelezonMotor;
import Model.Motor.KalipMotor;
import Serial.SerialCommHandler;
import Serial.Commands.BeginProcessCommand;
import Serial.Commands.CancelProcessCommand;
import Serial.Commands.CommandHandler;
import Serial.Commands.ContinueProcessCommand;
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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoPageController  implements Initializable{

	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;
	
    @FXML
    private AnchorPane videoAnchorPane;
	
    @FXML
    private MediaView videoPlayer;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//file = new File("../KontrolPaneli/src/Assets/icons/vids/akil_intro.mp4");
		String mediaPath = "/Assets/icons/vids/akil_intro.mp4";
		//media = new Media(file.toURI().toString());
		media = new Media(getClass().getResource(mediaPath).toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		
		videoPlayer.setMediaPlayer(mediaPlayer);
		videoPlayer.setVisible(true);
		
		videoAnchorPane.getChildren().setAll(videoPlayer);
		
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
			 	
			 	ObjectDeserializer deserializer = new ObjectDeserializer() ;
			 	
			 	try {
			 		String userDir = System.getProperty("user.dir");
			        String fileName = "process.akl";
			        File file = new File(userDir, fileName);
			        Process process = new Process();
			        if (file.exists()) {
			            System.out.println("File already exists in deserring" + userDir);
			             process = (Process) deserializer.deserializeObject( file.getPath());
			            
			        } else {
			            try {
			                if (file.createNewFile()) {
			                    System.out.println("File created successfully in " + userDir);

			                    // Serialize the object to the file
			                    process = (Process) deserializer.deserializeObject( file.getPath());

			                    // Do other stuff with the newly created file here
			                } else {
			                    System.out.println("Failed to create the file.");
			                }
			            } catch (IOException e) {
			                System.out.println("An error occurred while creating the file: " + e.getMessage());
			            }
			        }
			        
			        // eğer deserialize edilen process null ise 
			        if(process == null) {
			        	process = new Process();
			        }
			        
					machine.setCurrentProcess(process);
					
					process.setParcaSayisiProperty(new SimpleIntegerProperty());
					process.setHedefSayiProperty(new SimpleIntegerProperty(process.getHedefSayi()));
					System.out.println(process.getIsim());
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("deserializer sikintisi");
				}
			 	
			 	
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
		       
		        this.videoAnchorPane.getChildren().setAll(sidebarRoot);
		        
        });
			
	}
	
	
	
	public SerialCommHandler initSerialComm() {
		String comPortName = "";
		CommandHandler comHandler;
		SerialCommHandler scm;
		
		
		// kart aranıyor...
		if(SerialPort.getCommPorts() != null) {
			
			SerialPort[] portList = SerialPort.getCommPorts();
			
			for(SerialPort port: portList) {
				System.out.println("sys name: " + port.getSystemPortName());
				System.out.println("desname: " + port.getDescriptivePortName() + "\n");
				// Buradaki if ile arduinoya otomatik olarak bağlanabiliyoruz.
				if(port.getDescriptivePortName().contains("CH340") || port.getDescriptivePortName().contains("Arduino")  || port.getDescriptivePortName().contains("acm")|| port.getDescriptivePortName().contains("USB2.0") || port.getDescriptivePortName().contains("CP210"))
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
		comHandler.registerCommand("D", new ContinueProcessCommand());
		
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
			 Alert alert = new Alert(Alert.AlertType.ERROR);
			 alert.setTitle("ERROR!");
			 alert.setHeaderText("BAĞLANTI SAĞLANAMADI, LÜTFEN RESETLEYİN!!");
			 alert.setContentText("Resetleme işe yaramıyorsa teknik destek için bize ulaşın.");
			 alert.showAndWait();
		}
		
		// thread içeriinde çağırılacak comhandler ekleniyor.
		scm.setComHandler(comHandler);

		return scm;
	}
}





