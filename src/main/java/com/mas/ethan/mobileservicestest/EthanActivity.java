package com.mas.ethan.mobileservicestest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EthanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ethan);
    }


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
}
