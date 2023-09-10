package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoPageController  implements Initializable{

	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;
	
    @FXML
    private MediaView videoPlayer;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		file = new File("../KontrolPaneli/src/Assets/icons/vids/akil_intro.mp4");	
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		videoPlayer.setMediaPlayer(mediaPlayer);
		videoPlayer.setVisible(true);
		
		mediaPlayer.play();
			
	}

}
