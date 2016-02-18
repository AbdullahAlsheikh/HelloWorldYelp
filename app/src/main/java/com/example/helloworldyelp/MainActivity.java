package com.example.helloworldyelp;

import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    String consumerKey = "dudmo3ssHxvpUP_i_Lw60A";
    String consumerSecret = "fOhwH5mUo_CyzX2D2vcDUc8FNw8";
    String token = "pppXtWQAG-DcMV5HbeS7Cax81Dua98ea";
    String tokenSecret = "NqEsSpS6aOJpWQCDT_ox_-HYZLU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tv = (TextView) findViewById(R.id.textView2);
        Button generator = (Button) findViewById(R.id.button);
        generator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Thread t = new Thread(){
                    public void run(){
                        try {
                            Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
                            String response = yelp.search("burritos", 30.361471, -87.164326);
                            //System.out.println("Result:" + response);

                            YelpParser yp = new YelpParser();
                            yp.setResponse(response);
                            yp.parseBusiness();
                            String activity = yp.getBusinessName(0);
                            //System.out.print(activity);
                            tv.setText(activity);
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
