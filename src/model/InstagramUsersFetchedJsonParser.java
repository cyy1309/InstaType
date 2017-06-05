package model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by eski on 2017/5/6.
 */
public class InstagramUsersFetchedJsonParser extends InstagramFetchedJsonParser {

    final int USERSIMILARNUMBER = 60;
    String[][] resultUserSimilarity = new String[USERSIMILARNUMBER][3];

    /*
    * 根据用户输入关键字找相关用户，并取前五个显示其ID，用户名和头像，可在前端显示
    */
    @Override
    public String[][] parseFetchedUsersJson(JSONObject json){
        JSONArray data = json.getJSONArray("data");
        int length = (data.length()<=USERSIMILARNUMBER ? data.length() : USERSIMILARNUMBER);

        for(int i=0; i<length;i++){
            JSONObject post = (JSONObject) data.get(i);
            resultUserSimilarity[i][0] = (String) post.get("id");
            resultUserSimilarity[i][1] = (String) post.get("username");
            resultUserSimilarity[i][2] = (String) post.get("profile_picture");
//			System.out.println(resultUserSimilarity[i][0]+","+resultUserSimilarity[i][1]+","+resultUserSimilarity[i][2]);
        }
        return resultUserSimilarity;
    }

}
