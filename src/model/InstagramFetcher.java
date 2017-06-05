package model;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InstagramFetcher{
	final String ACCESS_TOKEN = "204275777.e029fea.495e515bb2ff4da8b6cc566d7743db92";

	String response;

	
    public String[][] getFetched(String choice, String idOrName) throws IOException {
		try {
			/*System.out.print("Please input your choice:\n"
					+ "1.Get information about yourself account\n"
					+ "2.Get information about a user(A user ID is needed).\n"
					+ "3.Get the most recent media published by self account\n"
					+ "4.Get the most recent media published by a user(A user ID is needed).\n"
					+ "5.Get the list of recent media liked by selfaccount\n"
					+ "6.Get a list of users matching the query(A user ID is needed).\n");
		    */
			
		    //send HttpGetRequest
		    String apiLink = generateAPILink(choice, idOrName);

		    response = Jsoup.connect(apiLink).ignoreContentType(true).execute().body();
		    JSONObject jsonResponse = new JSONObject(response); 
		    
		    switch(choice) {
		    case "USER" :
		    	InstagramUsersFetchedJsonParser iufjp = new InstagramUsersFetchedJsonParser();
		    	return iufjp.parseFetchedUsersJson(jsonResponse);
		    case "IMAGE" :
				InstagramImagesFetchedJsonParser imfjp = new InstagramImagesFetchedJsonParser();
		    	return imfjp.parseFetchedImagesJson(jsonResponse);
	    	default:
	    		break;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

	public String generateAPILink(String choice, String idOrName) {
		switch(choice) {
		case "USER" :
			return "https://api.instagram.com/v1/users/search?q="+idOrName+"&access_token="+ACCESS_TOKEN;
		case "IMAGE" :
			return "https://api.instagram.com/v1/users/"+idOrName+"/media/recent/?access_token="+ACCESS_TOKEN;
		default:
			return "";
		}
	}
}



/*
 * 		case "1": //"Selfinfo":
			String Selfinfo = "https://api.instagram.com/v1/users/self/?access_token="+ACCESS_TOKEN;
			return Selfinfo;
			
		case "2": //"userIdinfo":
			System.out.print("Please input userId: ");
			Scanner userIdInfoScanner = new Scanner(System.in);	    
		    String USER_ID = userIdInfoScanner.next();
			String userIdinfo= "https://api.instagram.com/v1/users/"+USER_ID+"/?access_token= "+ACCESS_TOKEN;
			return userIdinfo;
			
		case "3": //"SelfRecentMedia":
			String SelfRecentMedia= "https://api.instagram.com/v1/users/self/media/recent/?access_token="+ACCESS_TOKEN;
			return SelfRecentMedia;
			
		case "4": //"userIdMedia":
			System.out.print("Please input userId: ");
			Scanner userIdMediaScanner = new Scanner(System.in);	    
		    String USER_ID_Media = userIdMediaScanner.next();
			String userIdMedia="https://api.instagram.com/v1/users/"+USER_ID_Media+"/media/recent/?access_token="+ACCESS_TOKEN;
			return userIdMedia;
			
		case "5": //"likedMedia":
			String selfLikedMedia="https://api.instagram.com/v1/users/self/media/liked?access_token="+ACCESS_TOKEN;
			return selfLikedMedia;
			
		case "6": //"userSearch":
			System.out.print("Please input the name of the user: ");
			Scanner inputScanner = new Scanner(System.in);	    
		    String NAME = inputScanner.next();
			String userSearch="https://api.instagram.com/v1/users/search?q="+NAME+"&access_token="+ACCESS_TOKEN;
			return userSearch;
			*/
