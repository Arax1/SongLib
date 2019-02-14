package view;

import Backend.Song;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SongLibController {
	
	@FXML ListView<Song> listView;
	
	@FXML Button add;
	@FXML Button edit;
	@FXML Button delete;
	
	@FXML TextField text_songname;
	@FXML TextField text_artist;
	@FXML TextField text_album;
	@FXML TextField text_year;
		
	
	private ObservableList<Song> obsList;

	public void start(Stage mainStage) {
		// create list of items
		// form arraylist
				
		Song newsong = new Song("Jesus Walks", "Kanye West", "College Dropout", 2001);
		Song newsong_2 = new Song("Chase Me", "Run the Jewles", "Baby Driver", 2016);
				
		obsList = FXCollections.observableArrayList(newsong, newsong_2);
		
		listView.setItems(obsList);
		disableButtons();

		//setting the listener for those itmes
		listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> selectedSong(mainStage));
		
	}

	private void selectedSong(Stage mainStage) {
		
		try {
			Song selected = obsList.get(listView.getSelectionModel().getSelectedIndex());
			enableButtons();
			
			text_songname.setText(selected.name);
			text_artist.setText(selected.artist);
			text_album.setText(selected.album);
			text_year.setText(Integer.toString(selected.year));
		}
		
		catch(IndexOutOfBoundsException e){
			
		}
		
	}
	
	private void disableButtons(){
		
		edit.setDisable(true);
		delete.setDisable(true);	
	}
	
	private void enableButtons(){
		
		edit.setDisable(false);
		delete.setDisable(false);	
	}
	
	
	// Method for when deselecting the item the user chose in the listview when you click outside the list
	public void deselect(){
		
		listView.getSelectionModel().clearSelection();
		disableButtons();
		
		text_songname.clear();
		text_artist.clear();
		text_album.clear();
		text_year.clear();
		
	}

}
