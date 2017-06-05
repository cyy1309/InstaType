package model;

import net.sf.json.JSONObject;

public interface JsonParser {
	String[][] parseFetchedImagesJson(JSONObject json);
	String[][] parseFetchedUsersJson(JSONObject json);
	String[] parseAnalysedJson(JSONObject json);
	String[][] parseMinedJson(JSONObject json);
}
