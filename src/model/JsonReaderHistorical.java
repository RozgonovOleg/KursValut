package model;

// necessary components are imported

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JsonReaderHistorical{
	
        // essential URL structure is built using constants 
    public static final String ACCESS_KEY = "e66fa57662156e118f77a2ab66e1ba1b";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "historical";
    
    private static ObservableList<HistoricalList> hList = FXCollections.observableArrayList(); 
    
    public ObservableList<HistoricalList> getlist() {
        return JsonReaderHistorical.hList;
    }    

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    // sendLiveRequest() function is created to request and retrieve the data
    public static void sendHistoricalRequest(String valute){
    //	Main.logger.log(Level.INFO, "starts historical rate ");
    	hList.clear();
    	   	
        // The following line initializes the HttpGet Object with the URL in order to send a request
    	for(int i = 1;i<10;i++){
    	
    	LocalDate daysAgo = LocalDate.now().minusDays(i);
    	daysAgo.format(DateTimeFormatter.ofPattern("uuuu-MMM-d"));
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&date="+daysAgo);

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
         
            
            Double r = exchangeRates.getJSONObject("quotes").getDouble("USD"+valute);
            String d=daysAgo.toString();
           
            HistoricalList list=new HistoricalList(r.toString(),d);
            
            hList.add(list);
            
            response.close();
        } catch (ClientProtocolException e) {            
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        } catch (IOException e) {
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        } catch (ParseException e) {
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        } catch (JSONException e) {
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        }        
      }    	
    }
}