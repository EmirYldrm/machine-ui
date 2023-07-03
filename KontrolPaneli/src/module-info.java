module KontrolPaneli {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.fazecast.jSerialComm;
	requires com.jfoenix;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controllers to javafx.graphics, javafx.fxml;
	
}
