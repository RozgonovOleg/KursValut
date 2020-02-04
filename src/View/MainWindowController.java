package View;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import model.HistoricalList;
import model.JsonReaderHistorical;
import model.JsonReaderLive;

 public class MainWindowController {
 
 public MainWindowController() {	
 }
	 
 @FXML
 private TableView<HistoricalList> historicalList=new TableView<HistoricalList>();
 @FXML
 private TableColumn<HistoricalList, String> PriceColumn;
 @FXML
 private TableColumn<HistoricalList, String> DateColumn;
 @FXML
 Label usduah = new Label();
 @FXML
 Button Check = new Button();
 @FXML 
 private ComboBox<String> valute = new ComboBox<String>();
 ObservableList<String> valutes = (FXCollections.observableArrayList(
 		"UAH","GBP", "EUR", "JPY"));
 JsonReaderHistorical JsonReaderHistorical=new JsonReaderHistorical();
 @FXML
 private void initialize(){
	 valute.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	      @Override
	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {	       
	      }
	    });
	usduah.setText(JsonReaderLive.sendLiveRequest());
	valute.setValue("UAH");
	valute.setItems(valutes);
	valute.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	      @Override
	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {	       
	      }
	    });
	
	
 }
 @FXML
 private void handleCheck() {	
	    
	    String val = valute.getValue();
	    model.JsonReaderHistorical.sendHistoricalRequest(val);
		historicalList.setItems(JsonReaderHistorical.getlist());
		DateColumn.setCellValueFactory(cellData -> cellData.getValue().selectedDateProperty());
		DateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		PriceColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().gethowCost()));
		PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
 }
 
 
}
