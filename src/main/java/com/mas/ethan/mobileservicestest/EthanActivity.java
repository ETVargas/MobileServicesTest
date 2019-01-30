package com.mas.ethan.mobileservicestest;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URI;

public class EthanActivity extends AppCompatActivity {

    public static String weatherStr = "";
    public static String compassStr = "";
    public static String locationStr = "";
    public static String artistStr = "";
    public static String getStr = "";
    public static String putStr = "";
    public static String tutorialStr = "";

    public static String fullStr = "";

    public static boolean saving = true;


    ProgressDialog progress;

    private static final String TEST_URL                   = "https://mobileservicestest-cc2a2.firebaseio.com/messages.json";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";
    private static final String TAG = "AATestFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ethan);
        weatherStr = "";
        compassStr = "";
        locationStr = "";
        artistStr = "";
        getStr = "";
        putStr = "";
        tutorialStr = "";
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullStr = weatherStr + " " + compassStr + " " + locationStr + " "
                + artistStr + " " + getStr + " " + putStr + " " + tutorialStr;
        if (fullStr.length() < 7) {
            fullStr = "Your constructed message will appear here.";
        }
        TextView ourTextView = (TextView)findViewById(R.id.myTextView);
        ourTextView.setText(fullStr);

        registerReceiver(receiver, new IntentFilter(ACTION_FOR_INTENT_CALLBACK));
    }


    @Override
    public void onPause()
    {
        super.onPause();

        unregisterReceiver(receiver);
    }


    // Screen transitions

    public void moveToWeatherScreen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, WeatherActivity.class);
        startActivity(intentCapture);
    }

    public void moveToCompassScreen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, CompassActivity.class);
        startActivity(intentCapture);
    }

    public void moveToLocationScreen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, LocationActivity.class);
        startActivity(intentCapture);
    }

    public void moveToArtistListScreen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, ArtistListActivity.class);
        startActivity(intentCapture);
    }

    public void moveToRestTestScreen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, RestTestActivity.class);
        startActivity(intentCapture);
    }

    public void moveToRestTest2Screen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, RestTest2Activity.class);
        startActivity(intentCapture);
    }

    public void moveToTutorialScreen(View v) {
        Intent intentCapture = new Intent(EthanActivity.this, TutorialActivity.class);
        startActivity(intentCapture);
    }




    public void saveMessage(View v) {
        saving = true;
        // the request
        InputStream inputStream = null;
        String result = "";

        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPut httpPUT = new
                    HttpPut("https://mobileservicestest-cc2a2.firebaseio.com/messages.json");
            String json = "";
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("weatherContent",weatherStr);
            jsonObject.put("locationContent",locationStr);
            jsonObject.put("compassContent",compassStr);
            jsonObject.put("getContent",getStr);
            jsonObject.put("putContent",putStr);
            jsonObject.put("artistContent",artistStr);
            jsonObject.put("tutorialContent",tutorialStr);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPUT.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPUT.setHeader("Accept", "application/json");
            httpPUT.setHeader("Content-type", "application/json");

            RestTask task = new RestTask(this, ACTION_FOR_INTENT_CALLBACK);
            task.execute(httpPUT);
            progress = ProgressDialog.show(this, "Getting Data ...", "Waiting For Results...", true);
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
    }

    public void loadMessage(View v) {
        saving = false;
        // the request
        try
        {
            HttpGet httpGet = new HttpGet(new URI(TEST_URL));
            RestTask task = new RestTask(this, ACTION_FOR_INTENT_CALLBACK);
            task.execute(httpGet);
            progress = ProgressDialog.show(this, "Getting Data ...", "Waiting For Results...", true);
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
    }


    /**
     * Our Broadcast Receiver. We get notified that the data is ready this way.
     */
    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // clear the progress indicator
            if (progress != null)
            {
                progress.dismiss();
            }
            String response = intent.getStringExtra(RestTask.HTTP_RESPONSE);
            //ourTextView.setText(response);

            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

            if (!saving) {
                // TODO: parse response
                fullStr = response;
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    weatherStr = jsonObj.getString("weatherContent");
                    locationStr = jsonObj.getString("locationContent");
                    compassStr = jsonObj.getString("compassContent");
                    getStr = jsonObj.getString("getContent");
                    putStr = jsonObj.getString("putContent");
                    artistStr = jsonObj.getString("artistContent");
                    tutorialStr = jsonObj.getString("tutorialContent");

                    fullStr = weatherStr + " " + compassStr + " " + locationStr + " "
                            + artistStr + " " + getStr + " " + putStr + " " + tutorialStr;
                    if (fullStr.length() < 7) {
                        fullStr = "Your constructed message will appear here.";
                    }

                    TextView ourTextView = (TextView)findViewById(R.id.myTextView);
                    ourTextView.setText(fullStr);

                } catch(Exception e) {
                    //Lazily catch
                    TextView ourTextView = (TextView)findViewById(R.id.myTextView);
                    ourTextView.setText("Error parsing");
                }



            }

            Log.i(TAG, "RESPONSE = " + response);
            //
            // my old json code was here. this is where you will parse it.
            //
        }
    };
}
