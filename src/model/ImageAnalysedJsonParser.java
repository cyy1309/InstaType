package model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ImageAnalysedJsonParser extends Parser{
	final double MATCHINGPOINT = 0.60;
	final int RESULTNUM = 10;
	String[] result = new String[RESULTNUM];
	int resultNum = 0;

	public String[] parseAnalysedJson(JSONObject json) {
		/*{ "responses": [ 
		            	{ "labelAnnotations": [ 
		            		{ "mid": "/m/01cd9", "description": "brand", "score": 0.5969167 }, 
		            		{ "mid": "/m/02cwm", "description": "design", "score": 0.54624486 } 
		            	] } 
		            ] } 
		*/
		//System.out.println(json);
		JSONArray res = (JSONArray) json.get("responses");
		JSONObject labelAnotations = (JSONObject) res.get(0);
		if(labelAnotations.has("labelAnnotations")) {
			JSONArray descriptions = (JSONArray) labelAnotations.get("labelAnnotations");
			for(int i=0; i<descriptions.length(); i++) {
				JSONObject description = new JSONObject(descriptions.get(i).toString());
				if ((double) description.get("score") >= MATCHINGPOINT) {
					result[resultNum] = description.get("description").toString();
					resultNum++;

				}
			}
		}
		return result;
	}
}