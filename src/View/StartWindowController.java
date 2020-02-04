package View;

import java.util.logging.Level;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartWindowController {
	
	private Main main = new Main();
	
	@FXML
	private Button Cancel;
	
	@FXML
	private Button Register;
	
	@FXML
	private Button SignIn;
		
	@FXML
	private void handleCancel() {	
		
		 Stage stage = (Stage) Cancel.getScene().getWindow();
		    stage.close();
	 }
	@FXML
	public void Register(ActionEvent event) throws Exception {               
	   
		try {
	    	main.Register();	    	
	    }
	    catch(Exception e) {
	    e.printStackTrace();
	    Main.logger.log(Level.SEVERE, "Exception: ", e);
	   }
	 }
	
	@FXML
	public void SignIn(ActionEvent event) throws Exception {               
	   
		try {
	    	main.SignIn();
	    	Stage stage = (Stage) Cancel.getScene().getWindow();
	        stage.close();
	    }
	    catch(Exception e) {
	    e.printStackTrace();
	    Main.logger.log(Level.SEVERE, "Exception: ", e);
	   }
	 }
}
