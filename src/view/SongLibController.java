package view;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SongLibController {
	
	@FXML ListView<String> listView;
	
	private ObservableList<String> obsList;

	public void start() {
		// create list of items
		// form arraylist
				
		obsList = FXCollections.observableArrayList("Song1", "Song2", "Song3", "Song4", "Song5", 
					"Song6", "Song7", "Song8", "Song9", "Song10", "Saints", "blah");
		
		listView.setItems(obsList);
		
	}

}
