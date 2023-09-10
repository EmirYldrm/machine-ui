module KontrolPaneli {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.fazecast.jSerialComm;
	requires com.jfoenix;
	requires javafx.graphics;
	requires javafx.media;
	
	opens application to javafx.graphics, javafx.fxml, javafx.scene.media;
	opens Controllers to javafx.graphics, javafx.fxml, javafx.scene.media;
	
}
