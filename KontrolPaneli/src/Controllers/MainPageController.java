package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;

import Model.ClockModel;
import Model.ConfigHandler;
import Model.MachineInfo;
import Model.Process;
import Serial.SerialCommHandler;
import Serial.Commands.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainPageController implements Initializable{

	/////////////////////////////////////////////////////////////////////////////////////////////////
	private MachineInfo machine;
	private CommandHandler comHandler;
	private SerialCommHandler scm;
	private ClockModel clock;
	private ConfigHandler handler = new ConfigHandler();
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
    @FXML
    private Button cancelProecssButton;
    
    @FXML
    private Label clockLabel;
    
    @FXML
    private Label currentProcessLabel;
    
    @FXML
    private Label currentProductCountLabel;

    @FXML
    private TextField enjeksiyonDistanceField;

    @FXML
    private Button enjeksiyonHomeButton;

    @FXML
    private Label enjeksiyonKonumLabel;

    @FXML
    private Button enjeksiyonLeftButton;

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
    private Button moveEnjeksiyonMotor;

    @FXML
    private Button moveKalipMotor;

    @FXML
    private Button pauseProcessButton;
    
    @FXML
    private Pane mainPane;

    @FXML
    private ProgressBar processBar;

    @FXML
    private Button processBeginButton;

    @FXML
    private Button processSettingButton;

    @FXML
    private Button resetButton;

    @FXML
    private Label targetProductCountLabel;
    
    @FXML
    private TextArea testField;
    

    // Functions
    @FXML
    void cancelProcess(MouseEvent event) {
    	this.scm.sendString("C");
    }
    
    @FXML
    void goToProcessPage(MouseEvent event) {

    }
    
    @FXML
    void pauseProject(MouseEvent event) {
    	// makine durdurulmuşsa devam, 
    	// durdurulmamışsa durdur komutu dönderiliyor.
    	
    	if(this.machine.isPaused == true) {
    		this.scm.sendString("D");
    		
    		this.machine.isPaused = false;
    		this.pauseProcessButton.setStyle("-fx-background-color:  #fcfc57 ; -fx-text-fill: white;");
    	}else {
			this.scm.sendString("W");
			this.machine.isPaused = true;
			
			this.pauseProcessButton.setStyle("-fx-background-color: #d31c1c ; -fx-text-fill: white;"); 

    	}
    }
    
    @FXML
    void startProcess(MouseEvent event) {
    	// makine üretime başlamadıysa başla komutu gönder 
    	if(MachineInfo.getInstance().isBegin == false) {
    		this.scm.sendString("B");
    		MachineInfo.getInstance().isBegin = true;
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uyarı");
            alert.setHeaderText("Üretim devam etmektedir.");
            alert.showAndWait();
    	}
    }
    
    @FXML
    void resetBoards(MouseEvent event) {
    	this.scm.sendString("R");
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
    void moveEnjeksiyonLeft(MouseEvent event) {
    	String str = enjeksiyonDistanceField.getText();
        try {
        	float distance = Float.parseFloat(str);
        	long stepCount = (long) (machine.getEnjeksiyonMotor().oneMMStepcount * distance);
        	this.scm.sendString("EE " + stepCount);
        	System.out.println(stepCount);

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
    void moveEnjeksiyonRight(MouseEvent event) {
    	String str = enjeksiyonDistanceField.getText();
        try {
        	float distance = Float.parseFloat(str);
        	long stepCount = (long) (machine.getEnjeksiyonMotor().oneMMStepcount * distance);
        	this.scm.sendString("EE " + -stepCount);
        	System.out.println(stepCount);

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
    void moveKalipLeft(MouseEvent event) {

    	String str = moldDistanceField.getText();
        try {
        	float distance = Float.parseFloat(str);
        	long stepCount = (long) (machine.getKalipMotor().oneMMStepcount * distance);
        	this.scm.sendString("KK " + -stepCount);

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
        	float distance = Float.parseFloat(str);
        	long stepCount = (long) (machine.getKalipMotor().oneMMStepcount * distance);
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
    
    // kullanım limiti güncelleyici
    public void incrementUsage() {
    	if(this.handler.getAccessFlag() != 1) {
		
	    	long curr = this.handler.getTotalDuration();
	    	this.handler.setTotalDuration(curr + 1);
	    	System.out.println("anlık "+ curr);
	    	
	    	// eger toplam kullanım limiti aşmışsa password iste.
	    	if(this.handler.isLimitReached()) {
	    		FXMLLoader passwordLoader = new FXMLLoader(getClass().getResource("/view/PasswordPage.fxml"));
		        Parent passwordRoot = null;
		        try {
		        	passwordRoot = passwordLoader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        this.mainPane.getChildren().setAll(passwordRoot);
	    	}
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
    
    public void initalizepage() {
		
		if(scm != null)
			testField.textProperty().bind(scm.commandProtperty());
		
		
		// bind etmek için floatpropery oluşturup lambda fonksiyonu ile bind işlemini gerçekleştiriyoruz.
		FloatProperty nozzleTemp = machine.getNozzleTempProperty();
		FloatProperty midTemp = machine.getMidTempProperty();
		FloatProperty backTemp = machine.getBackTempProperty();
    }
    
    
    // side bar'dan çağırılacak fonksiyon.
    public void setPageVisibility(boolean visible) {
        this.mainPane.setVisible(visible);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.scm = SerialCommHandler.getInstance();
		this.comHandler = scm.getComHandler();
		this.machine  = MachineInfo.getInstance();
		
		if(scm != null)
			testField.textProperty().bind(scm.commandProtperty());
		
		//scm.sendString("W");
		//scm.sendString("D");
		
		// bind etmek için floatpropery oluşturup lambda fonksiyonu ile bind işlemini gerçekleştiriyoruz.
		FloatProperty nozzleTemp = machine.getNozzleTempProperty();
		FloatProperty midTemp = machine.getMidTempProperty();
		FloatProperty backTemp = machine.getBackTempProperty();
		
		if(this.machine.getCurrentProcess() == null)
		{
			// here there will be a process deserializing codes in the future
			this.machine.setCurrentProcess(new Process());
		}
		else {
			currentProcessLabel.setText(this.machine.getCurrentProcess().getIsim());
		}
		
		IntegerProperty parcaSayisi = this.machine.getCurrentProcess().getHedefSayiProperty();
		targetProductCountLabel.textProperty().bind(Bindings.convert(parcaSayisi));
		
		// Saat başlatılıyor.
		ClockModel clock = new ClockModel();
		
		 Bindings.bindBidirectional(
	                clockLabel.textProperty(),
	               clock.timeProperty()
	     );
		clock.start();
		
		incrementUsage();
		//şifre kontroln kısmı
		Duration duration = Duration.minutes(1);
        Timeline timeline = new Timeline(new KeyFrame(duration, event -> incrementUsage()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
		
	}
}
