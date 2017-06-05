package model;

import java.io.IOException;

import org.jsoup.Jsoup;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TextMiner{

	final String LEIKI_TOKEN = "853e04d0-6f52-41fd-80d8-e6eb0408125b";

	String response;
	String[][] matchedItems;
	
	public String[][] mineText(String[] textGoogled) throws IOException {
			
		String apiLink = "https://analysis-trial.leiki.com/focus/api?method=analyse&apikey=" + LEIKI_TOKEN 
				+ "&format=json"
				+ "&target=instagram+";

		apiLink = apiLink + textGoogled[0];
		for(int i=1; textGoogled[i]!= null; i++) {
			apiLink = apiLink + "+" + textGoogled[i];
		}
		
		String classificationType1 = "iabtier2";
		String classificationType2 = "iabtier1";
		String classificationType3 = "tier3";
		String classificationType4 = "leikihighdef";

		String apiLink1 = apiLink + "&classification=" + classificationType1;
		String apiLink2 = apiLink + "&classification=" + classificationType2;
		String apiLink3 = apiLink + "&classification=" + classificationType3;
		String apiLink4 = apiLink + "&classification=" + classificationType4;

		System.out.println(apiLink1);
		System.out.println(apiLink2);
		System.out.println(apiLink3);
		System.out.println(apiLink4);

		response = Jsoup.connect(apiLink3).ignoreContentType(true).execute().body();
		JSONObject jsonResponse = new JSONObject(response);
		//System.out.println(response);
		TextMinedJsonParser mjp = new TextMinedJsonParser();
		return mjp.parseMinedJson(jsonResponse);
	}
}
