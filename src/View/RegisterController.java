package View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.MD5;
import model.UseDB;

public class RegisterController {
	
	@FXML
	private TextField UserName;
	
	@FXML
	private PasswordField Password;
	
	@FXML
	private Button Cancel;
	
	@FXML
	private Button Save;
	
	@FXML
	private void handleCancel() {	
		
		 Stage stage = (Stage) Cancel.getScene().getWindow();
		    stage.close();
	 }
	UseDB useDb=new UseDB();
	MD5 md5=new MD5();
	@FXML
	private void handleSave() {
		String User=UserName.getText();
		String Pass=Password.getText();		
		String PassMD5 = MD5.md5Custom(Pass+User);
		String UserMD5 = MD5.md5Custom(User);
		if (!UseDB.selectAllUsers(User).contains(UserMD5)){
		
		 if (User.length()!=0 && Pass.length()!=0){
			 UseDB.insertDB(UserMD5, PassMD5);
		     Stage stage = (Stage) Cancel.getScene().getWindow();
             stage.close();
            }
		 
        else{
        	Alert alert = new Alert(AlertType.WARNING);        		
            alert.setTitle("Type in username and password");
            alert.setHeaderText("Type in username and password");
            alert.setContentText("Type in username and password");
            alert.showAndWait();
            }   
		 }
		else{
        	Alert alert = new Alert(AlertType.WARNING);        		
            alert.setTitle(" username ");
            alert.setHeaderText(" username ");
            alert.setContentText("This username already exists");
            alert.showAndWait();
            }   
	}
}
