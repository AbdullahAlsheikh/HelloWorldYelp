package com.example.helloworldyelp;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Html;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

public class MainActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    Random ran = new Random();
    String consumerKey = "dudmo3ssHxvpUP_i_Lw60A";
    String consumerSecret = "fOhwH5mUo_CyzX2D2vcDUc8FNw8";
    String token = "yPhkb0u9cRxGE8ikWRkH3ceMCCpKYpQA";
    String tokenSecret = "-WoZd39mwu4X9iVDXo5bxDNOBBU";
    int RandRestaurant;
    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
    String response;
    YelpParser yp = new YelpParser();
    URL newurl;
    String activity, img_url, rating;
    Bitmap mIcon_val;

    int limit;
    Thread t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                limit = 10;
                yelp.setLimit(limit);

                Button generator = (Button) findViewById(R.id.button);


                //String htmlexample = "<body><h2>The Result<br></h2>";
                //tv.setText(Html.fromHtml(htmlexample, null, null));
                generator.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        RandRestaurant = ran.nextInt(limit);
                        t1 = new Thread() {
                            public void run() {
                                try {
                                    response = yelp.searchByLocation("restaurant", "1250 Bellflower Boulevard, Long Beach, CA");
                                    yp.setResponse(response);
                                    yp.parseBusiness();
                                    activity = yp.getBusinessName(RandRestaurant);
                                    rating = yp.getBusinessRating(RandRestaurant);

                                    TextView tv = (TextView) findViewById(R.id.textView2);
                                    String htmlexample = "<body><h2>"+activity+"<br></h2><p>" + rating + "<p><br> ";
                                    tv.setText(Html.fromHtml(htmlexample));
                                } catch (Exception e) {
                                    System.out.println("\n\n\nError:" + e.getMessage());
                                }//End catch
                            }//End run
                        }; //End thread

                        t2 = new Thread() {
                            public void run() {
                                try {
                                    response = yelp.searchByLocation("restaurant", "1250 Bellflower Boulevard, Long Beach, CA");
                                    yp.setResponse(response);
                                    yp.parseBusiness();
                                    img_url = yp.getBusinessImageURL(RandRestaurant);

                                    ImageView iv = (ImageView) findViewById(R.id.imageView);
                                    newurl = new URL(img_url);
                                    mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                    iv.setImageBitmap(mIcon_val);
                                } catch (Exception e) {
                                    System.out.println("\n\n\nError:" + e.getMessage());
                                }//End catch
                            }//End run
                        }; //End thread
                        t1.start();
                        t2.start();
                    }
                });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information
    }


}
