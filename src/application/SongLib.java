//Made by Sammy Berger and Anand Raju

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.SongLibController;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SongLib.fxml"));
			
			SplitPane root = (SplitPane)loader.load();
			
			SongLibController listController = loader.getController();
			listController.start(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Song List");
			primaryStage.setResizable(false);
			
			primaryStage.show();
			
			root.lookupAll(".split-pane-divider").stream()
            .forEach(div ->  div.setMouseTransparent(true) );
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
