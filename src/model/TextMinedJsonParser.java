package model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TextMinedJsonParser extends Parser{
	String[][] result = new String[20][2];
	
	public String[][] parseMinedJson(JSONObject json) {
		JSONObject data = (JSONObject) json.get("data");
		JSONArray items = (JSONArray) data.get("items");

		for(int i=0; i<items.length(); i++) {
			JSONObject item = (JSONObject) items.get(i);
			result[i][0] = item.get("title").toString();
			result[i][1] = item.get("relevance").toString();
		}
		return result;
	}
}