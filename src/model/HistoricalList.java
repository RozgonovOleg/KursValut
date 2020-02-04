package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HistoricalList {
	
	    private SimpleStringProperty howCost;
	    private StringProperty selectedDate;
	    public HistoricalList() {this(null,null);} 
	    public HistoricalList(String r,String selectedDate) {

	        this.howCost = new SimpleStringProperty(r);
	        this.selectedDate = new SimpleStringProperty(selectedDate);	       
	    }

	    public String gethowCost() {return howCost.get();}

	    public void sethowCost(String r) {howCost.set(r);}
	    
	    public StringProperty howCostProperty() {return howCost;}
	    
	    public String getDate() {return selectedDate.get();}

	    public void setDate(String selectedDate) {this.selectedDate.set(selectedDate);}

	    public StringProperty selectedDateProperty() {return selectedDate;}
	    
	    public String toString(){return gethowCost().toString()+" "+getDate();}
}