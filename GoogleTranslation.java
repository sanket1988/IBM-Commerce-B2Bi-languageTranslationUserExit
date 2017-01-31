package google.translation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import com.google.api.translate.Language;

public class GoogleTranslation {
  
 public String translateResult(String langFrom, String langTo, String word) {
	 
  String output = null;
  try {
  langFrom=Language.valueOf(langFrom.toUpperCase()).toString();
  langTo=Language.valueOf(langTo.toUpperCase()).toString();
  String url = "http://translate.googleapis.com/translate_a/single?"+"client=gtx&"+"sl=" + langFrom + "&tl=" + langTo + "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");    
  URL obj = new URL(url);
  HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
  con.setRequestProperty("User-Agent", "Mozilla/5.0");
 
  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
  String inputLine;
  StringBuffer response = new StringBuffer();
 
  while ((inputLine = in.readLine()) != null) {
   response.append(inputLine);
  }
  in.close();
  con.disconnect();
  
  JSONArray jsonArray = new JSONArray(response.toString());
  JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
  JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
  output=jsonArray3.get(0).toString();
  
  }
  catch(Exception e) {
  e.printStackTrace();
  }
  
  return output;
  
 }
}