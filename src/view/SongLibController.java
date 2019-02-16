package view;

import java.util.Collections;

import Backend.Song;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
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
	
	@FXML Text sn;
	@FXML Text ar;
	@FXML Text al;
	@FXML Text y;
		
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
	
	public void changeTable(ActionEvent e) {
		
		
		Button command = (Button)e.getSource();
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(command == add) {
			Song song = new Song(text_songname.getText(), text_artist.getText(), text_album.getText(), Integer.parseInt(text_year.getText()));
			obsList.add(song);
			Collections.sort(obsList);
			
			text_songname.clear();
			text_artist.clear();
			text_album.clear();
			text_year.clear();
			
			deselect();
		}
		
		else if(command == edit){
			Song song = new Song(text_songname.getText(), text_artist.getText(), text_album.getText(), Integer.parseInt(text_year.getText()));
			obsList.set(index, song);
			Collections.sort(obsList);
			
			text_songname.clear();
			text_artist.clear();
			text_album.clear();
			text_year.clear();
		}
		
		else{
			
			obsList.remove(index);
			deselect();
		}
		
	}
	
	
	private void selectedSong(Stage mainStage) {
		
		try {
			
			Song selected = obsList.get(listView.getSelectionModel().getSelectedIndex());
			enableButtons();
			
			sn.setText("Song Name: " + selected.name);
			ar.setText("Artist: "+ selected.artist);
			al.setText("Album: "+ selected.album);
			y.setText("Year: " + Integer.toString(selected.year));
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
		
		sn.setText("Song Name:");
		ar.setText("Artist:");
		al.setText("Album:");
		y.setText("Year:");
			
	}

}
