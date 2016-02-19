package com.example.helloworldyelp;

import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);


                final TextView tv = (TextView) findViewById(R.id.textView2);
                Button generator = (Button) findViewById(R.id.button);
                String htmlexample = "<body><h2>The Result<br></h2>";
                tv.setText(Html.fromHtml(htmlexample, null, null));
                generator.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //System.out.println("Within the Thread");
                Thread t = new Thread(){
                    public void run(){
                        try {

                            int RandResturant =  ran.nextInt(10  - 2) + 1;
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            System.out.println(ts);
                            Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
                            String response = yelp.search("burritos", 30.361471, -87.164326);
                            System.out.println(response);

                            //System.out.println("Result:" + response);
//                            YelpParser yp = new YelpParser();
//                            yp.setResponse(response);
//                            yp.parseBusiness();
//                            String activity = yp.getBusinessName(RandResturant);
//                            System.out.print(activity);
//                            String htmlexample = "<body><h2>The Result<br></h2><p>"+activity+"<p>";
//
//                            tv.setText(Html.fromHtml(htmlexample, null, null));
                              } catch (Exception e) {

                                  System.out.println("\n\n\nError:"+e.getMessage());
                                }

                             }
                         };

                       t.start();







            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information
    }


}
