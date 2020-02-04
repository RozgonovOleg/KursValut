package application;
	
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.UseDB;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	public static Logger logger = Logger.getLogger(Main.class.getName());
	
	@Override
	public void start(Stage primaryStage) {
		StartWindow();
		UseDB.createDB();
	}
	
	public void StartWindow() {
		try {
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/StartWindow.fxml"));
	         AnchorPane rootLayout = (AnchorPane) loader.load();
	         Scene scene = new Scene(rootLayout, 300, 100);
             Stage primaryStage = new Stage();
             primaryStage .setScene(scene);
             primaryStage.setTitle("StartWindow");
             primaryStage.show();
       } catch(Exception e) {
           e.printStackTrace();
           logger.log(Level.SEVERE, "Exception: ", e);
       }	
	}
	
	public void Register() {
		try {
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/Register.fxml"));
	         AnchorPane rootLayout = (AnchorPane) loader.load();
	         Scene scene = new Scene(rootLayout, 300, 300);
             Stage primaryStage = new Stage();
             primaryStage.setScene(scene);
             primaryStage.initModality(Modality.APPLICATION_MODAL);
             primaryStage.setTitle("Register");
             primaryStage.show();
       } catch(Exception e) {
           e.printStackTrace();
           logger.log(Level.SEVERE, "Exception: ", e);
       }
	}
	
	public void SignIn() {
		try {
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/SignIn.fxml"));
	         AnchorPane rootLayout = (AnchorPane) loader.load();
	         Scene scene = new Scene(rootLayout, 300, 300);
             Stage primaryStage = new Stage();
             primaryStage.setScene(scene);
             primaryStage.initModality(Modality.APPLICATION_MODAL);
             primaryStage.setTitle("Sign in");
             primaryStage.show();
       } catch(Exception e) {
           e.printStackTrace();
           logger.log(Level.SEVERE, "Exception: ", e);
       }
	}
	
	public void MainWindow() {
		
		try {
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/MainWindow.fxml"));
	         AnchorPane rootLayout = (AnchorPane) loader.load();
	         Scene scene = new Scene(rootLayout, 600, 600);
             Stage primaryStage = new Stage();
             primaryStage.setScene(scene);
             primaryStage.initModality(Modality.APPLICATION_MODAL);
             primaryStage.setTitle("MainWindow");
             primaryStage.show();
            // logger.log(Level.INFO, "starts main window ");
       } catch(Exception e) {
           e.printStackTrace();
           logger.log(Level.SEVERE, "Exception: ", e);
       }
	}
	
	public static void main(String[] args) {
		
		try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }  
		
		launch(args);
    }
}
