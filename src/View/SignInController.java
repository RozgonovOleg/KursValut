package View;

import java.util.logging.Level;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.MD5;
import model.UseDB;

public class SignInController {
	@FXML
	private TextField UserName;
	
	@FXML
	private PasswordField Password;
	
	@FXML
	private Button Cancel;
	
	@FXML
	private Button Ok;
	
	@FXML
	private void handleCancel() {	
		
		 Stage stage = (Stage) Cancel.getScene().getWindow();
		    stage.close();
	 }
	UseDB useDb=new UseDB();
	private Main main = new Main();
	MD5 md5=new MD5();
	@FXML
	private void handleOk() {
		String User=UserName.getText();
		String Pass=Password.getText();
		String UserMD5 = MD5.md5Custom(User);
		UseDB.selectDB(UserMD5);
		String PassInDB=UseDB.selectDB(UserMD5);
		String PassMD5 = MD5.md5Custom(Pass+User);
		
		if (PassMD5.equals(PassInDB)){
			try {
		    	main.MainWindow();
		    	Stage stage = (Stage) Cancel.getScene().getWindow();
		        stage.close();
		    }
		    catch(Exception e) {
		    e.printStackTrace();
		    Main.logger.log(Level.SEVERE, "Exception: ", e);
		   }
		}
		else
		    {Alert alert = new Alert(AlertType.WARNING);        		
            alert.setTitle("Wrong username or password");
            alert.setHeaderText("wrong username or password");
            alert.setContentText("wrong username or password");
            alert.showAndWait();
        }    
	}

}
