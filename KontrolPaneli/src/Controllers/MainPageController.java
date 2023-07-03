package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainPageController {

    @FXML
    private Label arkaLabel;

    @FXML
    private TextField arkaTempField;

    @FXML
    private Button arkaTempSetButton;

    @FXML
    private Button connectButton;

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
    private TextArea messageTextArea;

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
    private ComboBox<?> portBox;

    @FXML
    private Button processSettingButton;

    @FXML
    void connectToMachine(MouseEvent event) {
    	
    }
    
}
