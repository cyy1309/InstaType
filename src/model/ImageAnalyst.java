package model;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ImageAnalyst {
	
	String response;
	String[] matchedItems = new String[100];
	int matchedItemsNum = 0;
	
	public String[] analyseImages(String[][] imagesURL) throws IOException {
		for (int i = 0; i<imagesURL.length; i++) {
			System.out.print("Scanning pics No." + i + ", result: " );

			String[] result = analyseImage(imagesURL[i][0]);
			for (int j = 0; result[j] != null; j++) {
				matchedItems[matchedItemsNum] = result[j];
				matchedItemsNum++;
				System.out.print(matchedItems[matchedItemsNum-1]);
			}
			System.out.println();

		}
		return matchedItems;
	}
	
	public String[] analyseImage(String imageURL) throws IOException{
		String apiLink = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyBrgTSuPmnk_auSJTBmFE5bB4t27GPk2qA";
		String json;
		json = "{\n\"requests\":[\n{\n\"image\":{\n\"source\":{\n"
				+ "\"imageUri\":\""+imageURL+"\"\n}\n},\n"
				+ "\"features\":[\n{\n\"type\":\"LABEL_DETECTION\"\n}\n]\n}\n]\n}";
		Connection con = Jsoup.connect(apiLink)
				.header("Content-Type", "application/json")
				.ignoreContentType(true)
				.requestBody(json);
		response = con.post().body().toString();
		//response = Jsoup.connect(apiLink).ignoreContentType(true).execute().body();
		
		response = response.replace("<body>\n ", "");
		response = response.replace("</body>", "");

		JSONObject jsonResponse = new JSONObject(response.toString());

		ImageAnalysedJsonParser ajp = new ImageAnalysedJsonParser();

		return ajp.parseAnalysedJson(jsonResponse);
	}
}
