package model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by eski on 2017/5/6.
 */

public class InstagramImagesFetchedJsonParser extends InstagramFetchedJsonParser {

    String[][] resultImages = new String[20][2];

    //调用解析json的方法，解析result并得到userID, userName, userProfilePictureURL, imagesURL
    public String[][] parseFetchedImagesJson(JSONObject json) {

        JSONArray data = json.getJSONArray("data");
        for(int i=0; i<data.length(); i++ ) {
            JSONObject post = (JSONObject) data.get(i);
            JSONObject user = (JSONObject) post.get("user");
            resultImages[i][1] = (String) user.get("id");

            JSONObject image = (JSONObject) post.get("images");
            JSONObject imageURL = (JSONObject) image.get("standard_resolution");
            resultImages[i][0] = (String) imageURL.get("url");
        }
        return resultImages;
    }

}
