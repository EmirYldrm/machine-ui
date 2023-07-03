package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SideBarController implements Initializable{

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
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadPage("/view/MainPage.fxml");
	}

}