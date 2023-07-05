package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Model.MachineInfo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class MainPageController implements Initializable{

	/////////////////////////////////////////////////////////////////////////////////////////////////
	MachineInfo machine;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		machine  = MachineInfo.getInstance();
		machine.setNozzleSicaklik(400.3f);
		machine.setMidSicaklik(350.3f);
		machine.setBackSicaklik(850.3f);
		
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
