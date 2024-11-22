// This file defines the main application class, which loads and displays the UI.
// It sets up the main window and shows the temperature graph interface.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TemperatureGraph extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	
        // Loads the FXML file and connects the UI
        Parent root = FXMLLoader.load(getClass().getResource("TemperatureGraph.fxml"));
        
        // Creates a scene with the loaded UI components
        Scene scene = new Scene(root);
        
        // Set the window title and scene
        stage.setTitle("TemperatureGraph");
        stage.setScene(scene);
        stage.show();  // Show the window
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
