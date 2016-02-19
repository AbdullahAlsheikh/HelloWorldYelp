package com.example.helloworldyelp;
import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import java.lang.Object;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;



public class YelpParser {

    private String yelp_response;
    //private Bundle yelp_bundle = new Bundle();
    private ArrayList<String> keys = new ArrayList<String>();
    private HashMap<String, String> yelp_bundle = new HashMap<String, String>();

    /**
     * Sets Yelp's response for this class
     * @param response
     */
    public void setResponse(String response){yelp_response = response;}

    /**
     * Returns the set Yelp response
     * @return string yelp_response
     */
    public String getResponse(){return yelp_response;}

    /**
     * Parse's yelp's response for the business name; mobile url; and ratings url.
     * Mobile url and ratings url is separated by " ,,, "
     * @sets yelp_bundle(key = business name)
     * @sets keys arraylist with business name
     * @throws JSONException
     */
    public void parseBusiness() throws JSONException {
        JSONObject o1 = new JSONObject(yelp_response);
        JSONArray businesses = o1.getJSONArray("businesses");
        String tmpString;
        for (int i = 0; businesses.length() > i; i++){
            tmpString = "yMobileUrl" + businesses.getJSONObject(i).get("mobile_url").toString() + "yRating" +
                    businesses.getJSONObject(i).get("rating").toString() + "yLocation" +
                    businesses.getJSONObject(i).get("location").toString() + "yImageUrl" +
                    businesses.getJSONObject(i).get("image_url").toString();
            keys.add(businesses.getJSONObject(i).get("name").toString());
            yelp_bundle.put(keys.get(i), tmpString);
        }
    }


    /**
     * Gets the business name, assuming you supply the bundle, key, and int
     * In reality, all it does is pull myKey.get(i), but I added the myBundle
     * param to try and force all information to be there.
     * @param myBundle
     * @param myKey
     * @param i
     * @return myKey.get(i)
     */
    public String getBusinessName(HashMap<String, String> myBundle, ArrayList<String> myKey, int i){return myKey.get(i);}

    /**
     * This gets the business's name, which is stored in the ArrayList keys, using
     * this class's stored results.
     * @param i
     * @return
     */
    public String getBusinessName(int i){return keys.get(i);}

    /**
     * This returns the business's mobile URL from myBundle using the key provided at int i
     * @param myBundle
     * @param myKey
     * @param i
     * @return mobileURL
     */
    public String getBusinessMobileURL(HashMap<String, String> myBundle, ArrayList<String> myKey, int i){
        //String tmp = myBundle.getString(myKey.get(i));
        String tmp = myBundle.get(myKey.get(i));
        int x = tmp.indexOf("yMobileUrl") + 10;
        int y = tmp.indexOf("yRating");
        String mobileURL = tmp.substring(x, y);
        return mobileURL;
    }

    /**
     * This returns the mobile URL using this class's internally stored variables at int i.
     * For ease of use I suggest using this method.
     *
     * @param i
     * @return mobileURL
     */
    public String getBusinessMobileURL(int i){
        //String tmp = yelp_bundle.getString(keys.get(i));
        String tmp = yelp_bundle.get(keys.get(i));
        int x = tmp.indexOf("yMobileUrl") + 10;
        int y = tmp.indexOf("yRating");
        String mobileURL = tmp.substring(x, y);
        return mobileURL;
    }

    public String getBusinessRating(int i){
        String tmp = yelp_bundle.get(keys.get(i));
        int x = tmp.indexOf("yRating") + 7;
        int y = tmp.indexOf("yLocation");
        String rating = tmp.substring(x, y);
        return rating;
    }

    public String getBusinessAddress(int i){
        String tmp, tmp2, address;
        int x, y;
        //Get the location information
        tmp = yelp_bundle.get(keys.get(i));
        x = tmp.indexOf("yLocation") + 9;
        y = tmp.indexOf("yImageUrl");
        tmp = tmp.substring(x, y);

        //Get the address
        x = tmp.indexOf("address") + 11;
        tmp2 = tmp.substring(x, tmp.length());
        y = tmp2.indexOf("\"],");
        address = tmp2.substring(0, y) + ", ";

        //Get the city
        x = tmp.indexOf("city") + 7;
        tmp2 = tmp.substring(x, tmp.length());
        y = tmp2.indexOf("\",");
        address = address + tmp2.substring(0, y) + ", ";

        //Get the state code
        x = tmp.indexOf("state_code") + 13;
        tmp2 = tmp.substring(x, tmp.length());
        y = tmp2.indexOf("\"");
        address = address + tmp2.substring(0, y) + " ";

        //Get the postal code
        x = tmp.indexOf("postal_code") + 14;
        tmp2 = tmp.substring(x, tmp.length());
        y = tmp2.indexOf("\",");
        address = address + tmp2.substring(0, y);

        return address;
    }

    public String getBusinessImageURL(int i){
        String tmp = yelp_bundle.get(keys.get(i));
        int x = tmp.indexOf("yImageUrl") + 9;
        String imgUrl = tmp.substring(x, tmp.length());
        return imgUrl;
    }

    /**
     * Returns the bundle, key is the business name.
     * @return yelp_bundle
     */
    public HashMap<String, String> getYelpBundle(){return yelp_bundle;}

    /**
     * Returns the keys (business names) for the yelpBundle.
     * @return keys (business names)
     */
    public ArrayList<String> getBundleKeys(){return keys;}

    /**
     * This will return the keys.size(), and is designed to be used with loops
     * @return keys.size()
     */
    public int getBudleKeysSize(){int size = keys.size(); return size; }
}