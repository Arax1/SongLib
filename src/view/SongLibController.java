package view;

import Backend.Song;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SongLibController {
	
	@FXML ListView<Song> listView;
	
	private ObservableList<Song> obsList;

	public void start() {
		// create list of items
		// form arraylist
				
		Song newsong = new Song("Jesus Walks", "Kanye West", "College Dropout", 2001);
		obsList = FXCollections.observableArrayList(newsong);
		
		listView.setItems(obsList);
		
	}

}
