//Made by Sammy Berger and Anand Raju

package view;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Backend.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

	Stage stage_var;

	public void start(Stage mainStage) {
		// create list of items
		// form arraylist
		stage_var = mainStage;
		obsList = FXCollections.observableArrayList(load("songlibrary.txt"));

		listView.setItems(obsList);
		disableButtons();

		//setting the listener for those items
		listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> selectedSong(mainStage));
		
		if(obsList.size() > 0)
			listView.getSelectionModel().select(0);
	}

	public void changeTable(ActionEvent e) {

		Button command = (Button)e.getSource();
		int index = listView.getSelectionModel().getSelectedIndex();

		if(command == add) {
			Song song = readSong(new Song());

			if(song.name.length() > 0 && song.artist.length() > 0) {

				if(!obsList.contains(song)) {
					obsList.add(song);
					Collections.sort(obsList);

					text_songname.clear();
					text_artist.clear();
					text_album.clear();
					text_year.clear();
					
					listView.getSelectionModel().select(obsList.indexOf(song));
				}

				else {
					errorMessage("Song already exists!");
				}
			} else {
				errorMessage("Each song needs a name and an artist!");
			}
		}

		else if(command == edit){
			Song song = readSong(obsList.get(index));

			if(song.name.length() > 0 && song.artist.length() > 0) {
				if(song.equals(obsList.get(index)) || !obsList.contains(song)) {
					//System.out.println("valid edit!");
					
					obsList.set(index, song);
					Collections.sort(obsList);

					text_songname.clear();
					text_artist.clear();
					text_album.clear();
					text_year.clear();
					
					listView.getSelectionModel().select(obsList.indexOf(song));
				}

				else {
					errorMessage("Song already exists!");
				}
			} else {
				errorMessage("Each song needs a name and an artist!");
			}
		}

		else{

			obsList.remove(index);
			if(obsList.size() > 0) {
				
				if(index == obsList.size()) 
					index--;				
				
				listView.getSelectionModel().select(index);
			}
			
			else {
				
				sn.setText("Song Name:");
				ar.setText("Artist:");
				al.setText("Album:");
				y.setText("Year:");
				disableButtons();
			}
				
		}

		save("songlibrary.txt");
	}

	private void selectedSong(Stage mainStage) {

		try {

			Song selected = obsList.get(listView.getSelectionModel().getSelectedIndex());
			enableButtons();

			sn.setText("Song Name: " + selected.name);
			ar.setText("Artist: "+ selected.artist);

			if(selected.album == null)
				al.setText("Album: "+ "N/A");

			else
				al.setText("Album: "+ selected.album);

			if(selected.year == 0)
				y.setText("Year: "+ "N/A");

			else
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

	private Song readSong(Song s) {
		Song song = new Song();
		
		String text = text_songname.getText();
		text = removeExternalSpaces(text);
		if(text.length() > 0)
			song.name = text;
		else
			song.name = s.name;

		text = text_artist.getText();
		text = removeExternalSpaces(text);
		if(text.length() > 0)
			song.artist = text;
		else
			song.artist = s.artist;
		
		text = text_album.getText();
		text = removeExternalSpaces(text);
		if(text.length() > 0)
			song.album = text;
		else
			song.album = s.album;
		
		//System.out.println("got here?");
		
		text = text_year.getText();
		text = removeExternalSpaces(text);
		if(text.length() > 0)
			try {
				song.year = Integer.parseInt(text);
			}
			catch(NumberFormatException ne){
				errorMessage("That's not a valid year!");
			}
		else
			song.year = s.year;
		
		//System.out.println("resulted in " + song.toString() + " | " + song.album + " | " + song.year);
		
		return song;
	}
	
	public static String removeExternalSpaces(String s) {
		int starting = 0, ending = 0;
		
		if(s.length() == 0)
			return s;
		
		for(starting  = 0; starting < s.length(); starting++) {
			if(s.charAt(starting) != ' ')
				break;
		}
		
		if(starting == s.length())
			return "";
		
		for(ending = s.length(); ending > 0; ending--) {
			if(s.charAt(ending-1) != ' ')
				break;
		}
		
		//System.out.println(s + ", [" + s.substring(starting,ending) + "]");
		
		return s.substring(starting, ending);
	}

	private void errorMessage(String message) {
		/* To be completed */

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(stage_var);
		alert.setTitle("Error!");
		alert.setHeaderText(message);
		alert.showAndWait();
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

	public void save(String filename) {

		try {
			File f = new File(filename);
			PrintWriter pw = new PrintWriter(f);

			pw.println(obsList.size());

			for(Song s: obsList) {
				pw.println(s.name);
				pw.println(s.artist);
				pw.println(s.album);
				pw.println(s.year);
			}

			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("something went wrong?");
		}
	}

	public ArrayList<Song> load(String filename) {
		try {
			File f = new File(filename);
			Scanner s = new Scanner(f);

			ArrayList<Song> al = new ArrayList<Song>();
			Song song;

			int numsongs = s.nextInt();
			s.nextLine();

			for(int x = 0; x < numsongs; x++) {
				song = new Song();
				song.name = s.nextLine();
				song.artist = s.nextLine();
				song.album = s.nextLine();
				song.year = s.nextInt();
				s.nextLine();
				al.add(song);
			}

			s.close();
			return al;

		} catch (Exception e) {
			
		}

		return new ArrayList<Song>();
	}

}
