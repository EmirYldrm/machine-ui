package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.PasswordHandler;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class PasswordController implements Initializable{

	
	///////////////////////
    @FXML
    private Button authenticateButton;
    
    @FXML
    private Button showHideButton;

    @FXML
    private Pane pane;

    @FXML
    private TextField passwordField;

    @FXML
    void checkPassword(ActionEvent event) {
    	
    	String passTry = passwordField.getText();
    	System.out.println(passTry);
    	String encryptedStr = PasswordHandler.hashString(passTry);
    	
    	if(PasswordHandler.compare(encryptedStr)) {
    		
    		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/view/SideBar.fxml"));
	        Parent mainRoot = null;
			try {
				mainRoot = mainLoader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	       
	        this.pane.getChildren().setAll(mainRoot);
    	}else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("YANLIŞ ŞİFRE GİRDİNİZ. TEKRAR DENEYİNİZ");
    		alert.showAndWait();
    	}
    }
    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}


}
