package model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class JsonReaderLive{

        // essential URL structure is built using constants 
    public static final String ACCESS_KEY = "e66fa57662156e118f77a2ab66e1ba1b";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    // sendLiveRequest() function is created to request and retrieve the data
    public static String sendLiveRequest(){

        // The following line initializes the HttpGet Object with the URL in order to send a request
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
            
            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000)); 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(timeStampDate);
                        
            String USDUAH = "1 " + exchangeRates.getString("source") + " in UAH : " + exchangeRates.getJSONObject("quotes").getDouble("USDUAH") + " (Date: " + formattedDate + ")";
            String USDEUR = "1 " + exchangeRates.getString("source") + " in EUR : " + exchangeRates.getJSONObject("quotes").getDouble("USDEUR") + " (Date: " + formattedDate + ")"; 
            String USDGBP = "1 " + exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP") + " (Date: " + formattedDate + ")";
            String USDJPY = "1 " + exchangeRates.getString("source") + " in JPY : " + exchangeRates.getJSONObject("quotes").getDouble("USDJPY") + " (Date: " + formattedDate + ")";
            return USDUAH+"\n"+USDEUR+"\n"+USDGBP+"\n"+USDJPY;
        } catch (ClientProtocolException e) {            
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        } catch (IOException e) {
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Main.logger.log(Level.SEVERE, "Exception: ", e);
        }
		return null;
    }
}